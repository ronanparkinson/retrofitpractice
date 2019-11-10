package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ronanp.retrofitpractice.R;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import model.RetroPerson;

public class ApiAdapter extends RecyclerView.Adapter<ApiAdapter.CreateViewHolder> {
    private List<RetroPerson> retroPeople;
    private Context context;

    public ApiAdapter(Context context, List<RetroPerson> retroPeople){
        this.context = context;
        this.retroPeople = retroPeople;
    }

      class CreateViewHolder extends RecyclerView.ViewHolder {
        public View view = null;
        TextView textView;
        private ImageView imageView;

        public CreateViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            textView = view.findViewById(R.id.title);
        }
    }

    @NonNull
    @Override
    public CreateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CreateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreateViewHolder holder, int position) {
        holder.textView.setText(retroPeople.get(position).getName());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(retroPeople.get(position).getAge())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return retroPeople.size();
    }

}
