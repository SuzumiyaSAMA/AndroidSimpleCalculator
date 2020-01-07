package com.test.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	TextView tv_result;
	private String operator = "";	//操作符
	private String firstNum = "";	//前一个操作数
	private String nextNum = "";	//后一个操作数
	private String result = "";		//当前计算结果
	private String showText = "";	//显示的文本内容

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_result = this.findViewById(R.id.tv_display);
	}

	public void onClick(View v){
		int resid = v.getId();
		String  inputText;
		if(resid ==R.id.ib_sqrt){
			inputText = "√";
		} else{
			inputText = ((TextView)v).getText().toString();
		}
		Log.d(TAG, "resid="+resid+",inputText="+inputText);

		if(resid == R.id.btn_clear){
			clear("");
		} else if (resid == R.id.btn_cancel){
			if(operator.equals("")){
				if (firstNum .length() == 1){
					firstNum = "0";
				} else if (firstNum.length() > 0) {
					firstNum = firstNum.substring(0, firstNum.length() - 1);
				} else {
					Toast.makeText(this, "没有可用的数字了", Toast.LENGTH_SHORT).show();
					return;
				}
				showText = firstNum;
				tv_result.setText(showText);
			} else {
				if (nextNum.length() == 1){
					nextNum = "";
				} else if (nextNum.length() > 0){
					nextNum = nextNum.substring(0, nextNum.length()-1);
				} else {
					Toast.makeText(this, "没有可取消的数字了", Toast.LENGTH_SHORT).show();
					return;
				}
				showText = showText.substring(0, showText.length()-1);
				tv_result.setText(showText);
			}
		} else if (resid == R.id.btn_equal){
			if (operator.length() == 0 ||operator.equals("==")){
				Toast.makeText(this, "请输入运算符", Toast.LENGTH_SHORT).show();
				return;
			} else if (nextNum.length() <= 0){
				Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
				return;
			}
			if (caculate()){
				operator = inputText;
				showText = showText+"="+result;
				tv_result.setText(showText);
			} else {
				return;
			}
		} else if (resid == R.id.btn_plus || resid == R.id.btn_minus
					|| resid == R.id.btn_multiply || resid == R.id.btn_divide){
			if (firstNum.length() <= 0){
				Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
				return;
			}
			if (operator.length() == 0 || operator.equals("=") || operator.equals("√")){
				operator = inputText;	//操作符
				showText = showText+operator;
				tv_result.setText(showText);
			} else {
				Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
				return;
			}
		} else if (resid == R.id.ib_sqrt){
			if (firstNum.length() <= 0){
				Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
				return;
			} else if (Double.parseDouble(firstNum) < 0){
				Toast.makeText(this, "开根号的数值不能小于0", Toast.LENGTH_SHORT).show();
				return;
			}
			result = String.valueOf(Math.sqrt(Double.parseDouble(firstNum)));
			firstNum = result;
			nextNum = "";
			operator = inputText;
			showText = showText + "√=" + result;
			tv_result.setText(showText);
			Log.d(TAG, "result="+result+",firstNum="+firstNum+",operator="+operator);
		} else {
			if (operator.equals("=")){
				operator = "";
				firstNum = "";
				showText = "";
			}
			if (resid == R.id.btn_dot){
				inputText = ".";
			}
			if (operator.equals("")){
				firstNum = firstNum + inputText;
			} else {
				nextNum = nextNum + inputText;
			}
			showText = showText+inputText;
			tv_result.setText(showText);
		}

	}

	private void clear(String text){
		showText = text;
		tv_result.setText(showText);
		operator = "";
		firstNum = "";
		nextNum = "";
		result = "";
	}

	private boolean caculate(){		//开始加减乘除四则运算
		if (operator.equals("+")){
			result = String.valueOf(Arith.add(firstNum, nextNum));
		} else if (operator.equals("-")){
			result = String.valueOf(Arith.sub(firstNum, nextNum));
		} else if (operator.equals("*")){
			result = String.valueOf(Arith.mul(firstNum, nextNum));
		} else if (operator.equals("/")){
			if ("0".equals(nextNum)){
				Toast.makeText(this, "被除数不能为零", Toast.LENGTH_SHORT).show();
				return false;
			} else {
				result = String.valueOf(Arith.div(firstNum, nextNum));
			}
		}
		firstNum = result;
		nextNum = "";
		return true;
	}

}

