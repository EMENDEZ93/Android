package app.andriod.em.andriode.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.andriod.em.andriode.pojo.Usuario;

public class UsuarioJSONParser {

    public static List<Usuario> parser(String content){
        try {
            JSONArray jsonArray = new JSONArray(content);
            List<Usuario> usuarioList = new ArrayList<>();

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Usuario usuario = new Usuario();

                usuario.setUsariosId(jsonObject.getInt("usuarioid"));
                usuario.setNombre(jsonObject.getString("nombre"));
                usuario.setTwitter(jsonObject.getString("twitter"));

                usuarioList.add(usuario);
            }
            return usuarioList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}
