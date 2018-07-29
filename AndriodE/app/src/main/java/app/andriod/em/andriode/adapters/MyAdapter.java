package app.andriod.em.andriode.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.andriod.em.andriode.R;
import app.andriod.em.andriode.pojo.Usuario;

public class MyAdapter extends BaseAdapter {

    List<Usuario> usuarioList = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public MyAdapter(Context context, List<Usuario> usuarioList){
        this.context = context;
        this.usuarioList = usuarioList;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return usuarioList.size();
    }

    @Override
    public Usuario getItem(int position) {
        return usuarioList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.item, parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Usuario usuario = getItem(position);
        viewHolder.title.setText( usuario.getUsariosId() + " - " +
                                  usuario.getNombre());
        Picasso.with(context).load(usuario.getTwitter()).into(viewHolder.imageView);
        return convertView;
    }

    public class ViewHolder{
        TextView title;
        ImageView imageView;

        public ViewHolder (View item){
            title = item.findViewById(R.id.title);
            imageView = (ImageView) item.findViewById(R.id.imageview);

        }

    }
}
