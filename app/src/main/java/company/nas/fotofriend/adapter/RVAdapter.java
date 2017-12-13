package company.nas.fotofriend.adapter;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import company.nas.fotofriend.Constant;
import company.nas.fotofriend.R;
import company.nas.fotofriend.entity.Album;
import company.nas.fotofriend.entity.Person;
import company.nas.fotofriend.entity.Photo;
import company.nas.fotofriend.mvp.IChangeFragment;
import company.nas.fotofriend.mvp.friendList.view.FriendFragment;
import io.realm.RealmObject;

/**
 * Created by admin on 13.12.2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<? extends RealmObject> personList = new ArrayList<>();
    private Context context;
    private String token;
    private int type;
    private int width;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Constant.LIST:
                View personView = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
                return new ViewHolderList(personView);
            case Constant.GALLERY:
                View galleryView = LayoutInflater.from(parent.getContext()).inflate(R.layout.galery_item, parent, false);
                return new ViewHolderGallery(galleryView);
            case Constant.PREVIEW:
                View photoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.galery_item, parent, false);
                return new ViewHolderPhoto(photoView);
        }
        return null;
    }

    public RVAdapter(List<? extends RealmObject> personList, Context context, String token, int type) {
        this.personList = personList;
        this.context = context;
        this.token=token;
        this.type = type;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case Constant.LIST:
                ViewHolderList viewHolderList = (ViewHolderList) holder;
                String text = ((Person) personList.get(position)).getLast_name() + " " + ((Person) personList.get(position)).getFirst_name();
                String path = ((Person) personList.get(position)).getPhoto_50();

                viewHolderList.tvPersonInfo.setText(text);
                Picasso.with(context).load(path).into(viewHolderList.ivAvatar);
                break;
            case Constant.GALLERY:
                ViewHolderGallery viewHolderGallery = (ViewHolderGallery) holder;
                String albumThumb = ((Album) personList.get(position)).getThumb_src();
                Picasso.with(context).load(albumThumb).into(viewHolderGallery.ivGallery);
                break;

            case Constant.PREVIEW:
                ViewHolderPhoto viewHolderPhoto = (ViewHolderPhoto) holder;
                String photoURL = ((Photo) personList.get(position)).getSrc();
                Picasso.with(context).load(photoURL).into(viewHolderPhoto.ivGallery);
                break;
        }



    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return type ;
    }

    public class ViewHolderList extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvPersonInfo;
        ImageView ivAvatar;
        public ViewHolderList(View itemView) {
            super(itemView);
            tvPersonInfo = itemView.findViewById(R.id.tv_person_info);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
          //  ((IChangeFragment)context).onReplaceFragment();
            ((IChangeFragment)view.getContext()).onReplaceFragment(FriendFragment.Instance(token, Constant.GALLERY,((Person)personList.get(getAdapterPosition())).getUser_id()), Constant.GALLERY_TAG);
        }
    }
    public class ViewHolderGallery extends RecyclerView.ViewHolder implements View.OnClickListener {
        //TextView tvPersonInfo;
        ImageView ivGallery;
        public ViewHolderGallery(View itemView) {
            super(itemView);
          //  tvPersonInfo = itemView.findViewById(R.id.tv_person_info);
            ivGallery = itemView.findViewById(R.id.iv_gallery);
            ivGallery.getLayoutParams().width = getWidth() / 2;
            ivGallery.getLayoutParams().height = getWidth() / 2;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //  ((IChangeFragment)context).onReplaceFragment();
            ((IChangeFragment)view.getContext()).onReplaceFragment(FriendFragment.Instance(token, Constant.PREVIEW,((Album)personList.get(getAdapterPosition())).getOwner_id(), ((Album)personList.get(getAdapterPosition())).getAid()), Constant.GALLERY_TAG);
        }
    }
    public class ViewHolderPhoto extends RecyclerView.ViewHolder {
        //TextView tvPersonInfo;
        ImageView ivGallery;
        public ViewHolderPhoto(View itemView) {
            super(itemView);
            //  tvPersonInfo = itemView.findViewById(R.id.tv_person_info);
            ivGallery = itemView.findViewById(R.id.iv_gallery);
            ivGallery.getLayoutParams().width = getWidth() / 2;
            ivGallery.getLayoutParams().height = getWidth() / 2;
        }

    }

}
