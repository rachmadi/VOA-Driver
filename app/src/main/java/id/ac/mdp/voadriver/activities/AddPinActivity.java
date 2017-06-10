package id.ac.mdp.voadriver.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.mdp.voadriver.utils.AsteriskPasswordTransformation;
import id.ac.mdp.voadriver.R;
import id.ac.mdp.voadriver.utils.Utilities;

public class AddPinActivity extends AppCompatActivity {

    List<String> pinNumber=new ArrayList<>();
    EditText[] etPin=new EditText[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pin);

        etPin[0]=(EditText) findViewById(R.id.et_pin_1);
        etPin[1]=(EditText) findViewById(R.id.et_pin_2);
        etPin[2]=(EditText) findViewById(R.id.et_pin_3);
        etPin[3]=(EditText) findViewById(R.id.et_pin_4);

        etPin[0].setTransformationMethod(new AsteriskPasswordTransformation());
        etPin[1].setTransformationMethod(new AsteriskPasswordTransformation());
        etPin[2].setTransformationMethod(new AsteriskPasswordTransformation());
        etPin[3].setTransformationMethod(new AsteriskPasswordTransformation());
    }

    public void buttonInputPinClick(View view) {
        switch (view.getId()){
            case R.id.btn_input_pin_0: addPinNumber("0"); break;
            case R.id.btn_input_pin_1: addPinNumber("1"); break;
            case R.id.btn_input_pin_2: addPinNumber("2"); break;
            case R.id.btn_input_pin_3: addPinNumber("3"); break;
            case R.id.btn_input_pin_4: addPinNumber("4"); break;
            case R.id.btn_input_pin_5: addPinNumber("5"); break;
            case R.id.btn_input_pin_6: addPinNumber("6"); break;
            case R.id.btn_input_pin_7: addPinNumber("7"); break;
            case R.id.btn_input_pin_8: addPinNumber("8"); break;
            case R.id.btn_input_pin_9: addPinNumber("9"); break;
            case R.id.btn_input_pin_clear: clearPinNumber(); break;
            case R.id.btn_input_pin_done: checkPinNumber(); break;
        }
    }

    private void clearPinNumber() {
        pinNumber.clear();
        etPin[0].setText("");
        etPin[1].setText("");
        etPin[2].setText("");
        etPin[3].setText("");
        Utilities.vibrateFor(AddPinActivity.this,100);
    }

    private void checkPinNumber() {
        if(pinNumber.size()==4){
            Utilities.showAsToast(AddPinActivity.this,getString(R.string.message_pin_accepted), Toast.LENGTH_SHORT);
            finish();
        }else {
            Utilities.showAsToast(AddPinActivity.this,getString(R.string.message_error_pin_size), Toast.LENGTH_SHORT);
        }
        Utilities.vibrateFor(AddPinActivity.this,100);
    }

    private void addPinNumber(String input){
        if(pinNumber.size()<4){
            pinNumber.add(input);
            for(int i=0;i<pinNumber.size();i++){
                etPin[i].setText(pinNumber.get(i));
            }
            Utilities.vibrateFor(AddPinActivity.this,80);
        }
    }

}
