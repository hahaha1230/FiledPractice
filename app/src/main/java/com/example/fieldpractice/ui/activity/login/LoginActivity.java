package com.example.fieldpractice.ui.activity.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fieldpractice.R;
import com.example.fieldpractice.base.BaseActivity;

/**
 * Created by JG on 2019/5/3.
 */

public class LoginActivity extends BaseActivity<LoginPresenter,ILogin.VP> {

    private EditText etName;
    private EditText etPwd;
    private Button btLogin;

    @Override
    public LoginPresenter getPresenterInstance() {
        return new LoginPresenter();
    }

    @Override
    public void initView() {
        etName=(EditText)findViewById(R.id.et_name);
        etPwd=(EditText)findViewById(R.id. et_pwd);
        btLogin=(Button)findViewById(R.id.bt_login);

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
        btLogin.setOnClickListener(this);

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bt_login:
                String name=etName.getText().toString();
                String pwd=etPwd.getText().toString();

                //requestLogin(name,pwd);
                getContract().requestLogin(name,pwd);
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
}
