package com.vaibhavmojidra.calculator

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var ExpressionTB:EditText;
    private lateinit var ResultTB:EditText;
    private lateinit var Clear:TextView;
    private lateinit var Delete:TextView;
    private lateinit var Equal:TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ExpressionTB=findViewById(R.id.exp)
        Clear=findViewById(R.id.Clear)
        Delete=findViewById(R.id.Delete)
        Equal=findViewById(R.id.Equal)
        Clear.setOnClickListener(View.OnClickListener {
          ExpressionTB.setText("0")
        })
        Delete.setOnClickListener(View.OnClickListener {
            var ss:String=ExpressionTB.getText().toString().trim()
            if(ss.length>0){
                ss=ss.substring(0,ss.length-1)
                ExpressionTB.setText(ss)
            }else{
                ExpressionTB.setText("0")
            }
            if(ss.trim().equals("")){
                ExpressionTB.setText("0")
            }
        })
        Equal.setOnClickListener(View.OnClickListener {
            var chararr=ExpressionTB.getText().toString().toCharArray()
            var l=ExpressionTB.getText().toString().length
            var i=0
            while(i<l){
                if(chararr[i].equals('×')){
                    chararr[i]='*'
                }
                if(chararr[i].equals('−')){
                    chararr[i]='-'
                }
                i++
            }
            try{
                var calc =ExpressionBuilder(String(chararr)).build()
                var result=calc.evaluate()
                ExpressionTB.setText(result.toString())
            }catch(e:Exception){
                Toast.makeText(this,"Invalid Expression",Toast.LENGTH_LONG).show()
            }
        })
    }

    fun setAddValue(v: View) {
        val b: TextView = v as TextView
        var ss:String=ExpressionTB.getText().toString().trim()
        if(ss.equals("0")){
            if(b.getText().toString().trim().equals(".")){
                ExpressionTB.append(b.getText().toString())
            }else{
                ExpressionTB.setText(b.getText().toString())
            }
        }else{
            ExpressionTB.append(b.getText().toString())
        }

    }
}
