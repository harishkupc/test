package in.syncro.grabit.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import in.syncro.grabit.R;
import in.syncro.grabit.db.local.ObjPojo;
import in.syncro.grabit.listeners.ActivateClickListener;

public class CustomDialog extends Dialog implements View.OnClickListener {

    Context context;
    ObjPojo objPojo;
    ActivateClickListener listener;

    private TextView title, desc, amount;
    private ImageView logo;
    private Button btn;

    public CustomDialog(Context context, ObjPojo objPojo, ActivateClickListener listener) {
        super(context);
        this.context = context;
        this.objPojo = objPojo;
        this.listener = listener;
    }

    private int getWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        getWindow().setLayout(((getWidth() / 100) * 90), LinearLayout.LayoutParams.WRAP_CONTENT);


        btn = findViewById(R.id.btn);
        logo = findViewById(R.id.logo);
        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        amount = findViewById(R.id.amount);

        Glide.with(context)
                .load(objPojo.getImage())
                .into(logo);

        title.setText(objPojo.getTitle());
        desc.setText(objPojo.getDesc());
        amount.setText(objPojo.getAmount());

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                listener.onActivateClicked(objPojo);
                dismiss();
                break;
        }
    }
}
