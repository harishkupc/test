package in.syncro.grabit.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.syncro.grabit.R;
import in.syncro.grabit.adapter.RecyclerAdapter;
import in.syncro.grabit.base.BaseActivity;
import in.syncro.grabit.db.local.ObjPojo;
import in.syncro.grabit.listeners.ActivateClickListener;
import in.syncro.grabit.ui.OfferView;
import in.syncro.grabit.utils.CustomDialog;

public class MainActivity extends BaseActivity implements ActivateClickListener {

    List<ObjPojo> list = new ArrayList<>();
    @BindView(R.id.main)
    LinearLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        int i = 1;
        while (i < 11) {
            ObjPojo objPojo = new ObjPojo();
            objPojo.setImage("https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Android_Studio_icon.svg/512px-Android_Studio_icon.svg.png");
            objPojo.setTitle("WhatsApp" + i);
            objPojo.setDesc("Testing description for custom dialog");
            objPojo.setAmount("20.2");
            list.add(objPojo);
            i++;
        }

        int j = 0;
        while (j < 6) {
            OfferView offerView = new OfferView(this);

            if (j == 2) {
                LinearLayoutManager manager = new LinearLayoutManager(this);
                RecyclerAdapter adapter = new RecyclerAdapter(list, this, this, true);
                offerView.setAdapterManager(adapter, manager);
            } else {
                LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                RecyclerAdapter adapter = new RecyclerAdapter(list, this, this, false);
                offerView.setAdapterManager(adapter, manager);
            }

            if (j <= 5) {
                if (j > 1) {
                    offerView.setCustomTag("offers" + j);
                    offerView.setLeftText("Offers " + j);
                    if (j < 4) {
                        offerView.setRightText("More");
                    }
                    if (j == 4) {
                        offerView.setDescripsion("Testing Description");
                    }
                }

                if (j == 2) {
                    offerView.setLeftTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                }
                if (j == 3) {
                    offerView.setRightTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                    offerView.setRightOnClickListener(new OfferView.OnClicker() {
                        @Override
                        public void onClick(String tag, boolean isRight) {
                            if (tag.equalsIgnoreCase("offers3")) {
                                Toast.makeText(MainActivity.this, tag, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
            main.addView(offerView);
            j++;
        }

    }

    @Override
    public void onActivateClicked(ObjPojo objPojo) {
        new CustomDialog(this, objPojo, new ActivateClickListener() {
            @Override
            public void onActivateClicked(ObjPojo objPojo) {
                Toast.makeText(MainActivity.this, objPojo.getTitle(),
                        Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
}
