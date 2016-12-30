package aqcxbom.xposedhooktarget;

import aqcxbom.xposedhooktarget.ArgClass;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtnToast;
    private Button mBtnShowMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnShowMethods = (Button)findViewById(R.id.btnShowMethods);
        mBtnToast = (Button)findViewById(R.id.btnToast);
        mBtnShowMethods.setOnClickListener(this);
        mBtnToast.setOnClickListener(this);

        MyClass.helloWorld(20140830, "kerui cr18");
        //ShowMethods(MyClass.class);
        //ShowDeclaredMethods(MyClass.class);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnShowMethods:{
                ShowMethods();
            }
                break;
            case R.id.btnToast:{
                Toast();
            }
                break;
        }
    }
    private void ShowMethods(){
        ShowDeclaredMethods(MyClass.class);
    }
    private void Toast(){
        String[][] str = new String[2][3];
        Map m1 = new HashMap();
        m1.put(1, "HashMap");

        Map<String, String> m2 = new HashMap<String, String>();
        m2.put("m2", "this is HashMap<String, String>");

        Map<Integer, String> m3 = new HashMap<Integer, String>();
        m3.put(3, "this is HashMap<Integer, String>");

        ArrayList a1 = new ArrayList();
        a1.add("ArrayList1");

        ArrayList<Integer> a2 = new ArrayList<Integer>();
        a2.add(222);

        ArgClass cls = new ArgClass();
        Toast.makeText(MainActivity.this, "AqCxBoM fun1 result: " +
                "" + MyClass.fun1(str, m1, m2, m3, a1, a2, cls), Toast.LENGTH_SHORT).show();
    }
    private void ShowDeclaredMethods(Class cl)
    {
        Method[] methods = cl.getDeclaredMethods();
        MyClass.LOGI(cl.getClass().getName().toString() + " has DeclaredMethods:");
        for (Method mt:methods)
        {
            MyClass.LOGI(mt.getName().toString());

            Class[] Pt = mt.getParameterTypes();
            String ArgStr = "(";
            String ArgCur = "";
            Boolean bIsClassPath = false;
            for (Class clp: Pt)
            {
                bIsClassPath = false;
                //MyClass.LOGI(getClass_a(clp).getName().toString()); //[[Ljava.lang.String; java.util.Map           java.util.ArrayList
                ArgCur = clp.toString();
                //MyClass.LOGI("========= " + ArgCur); //class [[Ljava.lang.String; interface java.util.Map  class java.util.ArrayList

                //判断是不是需要调整的类参数
                if(ArgCur.startsWith("interface") || (ArgCur.startsWith("class") && !ArgCur.contains("[")))
                    bIsClassPath = true;
                if (!bIsClassPath)
                    ArgStr += getClass_a(clp).getName().toString();
                else
                    ArgStr += "L" + getClass_a(clp).getName().toString() + ";";
                ArgStr = ArgStr.replace(".", "/");
            }
            ArgStr += ")";
            MyClass.LOGI(ArgStr);
        }
    }

    private void ShowMethods(Class cl)
    {
        Method[] methods = cl.getMethods();
        MyClass.LOGI(cl.getClass().getName().toString() + " has methods:");
        for (Method mt:methods)
        {
            MyClass.LOGI(mt.getName().toString());
        }
    }

    public static Class getClass_a(Class cls) {
        if(cls.getName().equals("int")) {
            cls = Integer.class;
        }
        else if(cls.getName().equals("long")) {
            cls = Long.class;
        }
        else if(cls.getName().equals("short")) {
            cls = Short.class;
        }
        else if(cls.getName().equals("float")) {
            cls = Float.class;
        }
        else if(cls.getName().equals("double")) {
            cls = Double.class;
        }
        else if(cls.getName().equals("boolean")) {
            cls = Boolean.class;
        }

        return cls;
    }
}
