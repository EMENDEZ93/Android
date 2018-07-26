package app.andriod.em.andriode.parsers;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import app.andriod.em.andriode.pojo.Usuario;

public class UsuarioXMLParser {

    public static List<Usuario> parser(String content){
        boolean inDataItemTag = false;

        String currentTagName = "";
        Usuario usuario = null;
        List<Usuario> usuarioList = new ArrayList<>();

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(content));

            int evenType = parser.getEventType();
            while(evenType != XmlPullParser.END_DOCUMENT){
                switch (evenType){
                    case XmlPullParser.START_TAG:
                        currentTagName = parser.getName();
                        if(currentTagName.equals("usuario")){
                            inDataItemTag = true;
                            usuario = new Usuario();
                            usuarioList.add(usuario);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("usuario")){
                            inDataItemTag = false;
                        }
                        currentTagName = "";
                        break;
                    case XmlPullParser.TEXT:
                        if(inDataItemTag && usuario != null){
                            switch(currentTagName){
                                case "usuarioid":
                                    usuario.setUsariosId(Integer.parseInt(parser.getText()));
                                    break;
                                case "nombre":
                                    usuario.setNombre(parser.getText());
                                    break;
                                case "twitter":
                                    usuario.setTwitter(parser.getText());
                                    break;
                            }
                        }
                        break;
                }
                evenType = parser.next();
            }
            return usuarioList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
