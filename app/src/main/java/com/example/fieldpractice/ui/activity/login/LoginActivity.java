package com.example.fieldpractice.ui.activity.login;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fieldpractice.R;
import com.example.fieldpractice.base.BaseActivity;
import com.example.fieldpractice.utils.JellyInterpolator;

import org.reactivestreams.Subscription;

import java.nio.Buffer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JG on 2019/5/3.
 */

public class LoginActivity extends BaseActivity<LoginPresenter,ILogin.VP> {


    @BindView(R.id.tv_login)
    TextView tvLogin;

    @BindView(R.id.tv_login_cancel)
    TextView tvLoginCancel;

    @BindView(R.id.layout_progress)
    View progress;

    @BindView(R.id.input_layout)
    View mInputLayout;

    @BindView(R.id.input_layout_name)
    LinearLayout mName;

    @BindView(R.id.input_layout_psw)
    LinearLayout mPsw;

    @BindView(R.id.et_name)
    EditText etName;


    @BindView(R.id.et_pwd)
    EditText etPwd;

    private float mWidth, mHeight;

    @Override
    public LoginPresenter getPresenterInstance() {
        return new LoginPresenter();
    }

    @Override
    public void initView() {
        // 使通知栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ButterKnife.bind(this);
    }



    @Override
    public void initData() {


    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_layout;
    }

    @Override
    public void initListener() {
        tvLogin.setOnClickListener(this);
        tvLoginCancel.setOnClickListener(this);
    }

    @Override
    public void destroy() {

    }

    private Subscription subscription;
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_login:
                if (progress.getVisibility()==View.VISIBLE)
                {
                    Toast.makeText(this,"正在登陆中...",Toast.LENGTH_SHORT).show();
                    return;
                }


                // 计算出控件的高与宽
                mWidth = tvLogin.getMeasuredWidth();
                mHeight = tvLogin.getMeasuredHeight();
                // 隐藏输入框
                mName.setVisibility(View.INVISIBLE);
                mPsw.setVisibility(View.INVISIBLE);

                inputAnimator(mInputLayout, mWidth, mHeight);


                String name=etName.getText().toString();
                String pwd=etPwd.getText().toString();
                getContract().requestLogin(name,pwd);

                break;
            case R.id.tv_login_cancel:
                if (progress.getVisibility()==View.VISIBLE)
                {
                    progress.setVisibility(View.GONE);
                    //取消登录
                    //---------
                }
                break;
        }

    }
    /**
     * 面向接口
     * @return
     */
    @Override
    public ILogin.VP getContract() {
        return new ILogin.VP() {
            @Override
            public void requestLogin(String name, String pwd) {
                mPresenter.getContract().requestLogin(name,pwd);
            }

            @Override
            public void responseLoginResult(boolean loginStatusResult) {

                Toast.makeText(LoginActivity.this,"登陆状态为"+loginStatusResult,Toast.LENGTH_SHORT).show();
            }
        };
    }

    /*
    @Override
    public void requestLogin(String name, String pwd) {

        mPresenter.requestLogin(name,pwd);
    }

    @Override
    public void responseLoginResult(boolean loginStatusResult) {
        Toast.makeText(this,"登陆状态为"+loginStatusResult,Toast.LENGTH_SHORT).show();

    }*/

    @Override
    public <ERROR> void responseError(ERROR error, Throwable throwable) {
        Toast.makeText(this,"异常"+error,Toast.LENGTH_SHORT).show();
    }


    /**
     * 输入框的动画效果
     *
     * @param view
     *      控件
     * @param w
     *      宽
     * @param h
     *      高
     */
    private void inputAnimator(final View view, float w, float h) {

        AnimatorSet set = new AnimatorSet();
        ValueAnimator animator = ValueAnimator.ofFloat(0, w);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view
                        .getLayoutParams();
                params.leftMargin = (int) value;
                params.rightMargin = (int) value;
                view.setLayoutParams(params);
            }
        });
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout,
                "scaleX", 1f, 0.5f);
        set.setDuration(1000);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.playTogether(animator, animator2);
        set.start();
        set.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                /**
                 * 动画结束后，先显示加载的动画，然后再隐藏输入框
                 */
                progress.setVisibility(View.VISIBLE);
                progressAnimator(progress);
                mInputLayout.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });

    }

    /**
     * 出现进度动画
     *
     * @param view
     */
    private void progressAnimator(final View view) {
        PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
                0.5f, 1f);
        PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
                0.5f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(view,
                animator, animator2);
        animator3.setDuration(1000);
        animator3.setInterpolator(new JellyInterpolator());
        animator3.start();

    }
}
