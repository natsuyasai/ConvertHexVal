package com.nyasai.converthexval;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // HEX->DEC
        findViewById(R.id.btnHex2Dec).setOnClickListener((view)->{
            onClickHex2Dec(view);
        });

        // DEC->HEX
        findViewById(R.id.btnDec2Hex).setOnClickListener((view)->{
            onClickDec2Hex(view);
        });
    }

    /**
     * Hex2Decボタンクリック時処理
     * @param v ビュー
     */
    private void onClickHex2Dec(View v)
    {
        // 変換用生成
        ValueConverter converter = new HexConverter(
            (((EditText)findViewById(R.id.etHexValue)).getText().toString()),
            getSignedInfo());
        // 変換実行
        execConvert(converter,
            (findViewById(R.id.etDecValue)));
    }

    /**
     * Dec2Hexボタンクリック時処理
     * @param v ビュー
     */
    private void onClickDec2Hex(View v)
    {
        // 変換用生成
        ValueConverter converter = new DecConverter(
            (((EditText)findViewById(R.id.etDecValue)).getText().toString()),
            getSignedInfo());
        // 変換実行
        execConvert(converter,
            (findViewById(R.id.etHexValue)));
    }

    /**
     * 変換実行
     * @param converter 変換用クラス
     * @param etRslt 結果表示用EditText
     */
    private void execConvert(ValueConverter converter, EditText etRslt)
    {
        // 変換実行
        ConvertResult result = converter.convert();

        // 結果表示
        if(result.getRslt() == true)
            etRslt.setText(result.getStr());
        else {
            Toast toast = Toast.makeText(this, "入力値不正", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
    }

    /**
     * singed情報取得
     * @return
     */
    private boolean getSignedInfo()
    {
        return (((Spinner)findViewById(R.id.spnSinInfo)).getSelectedItem().toString().matches("signed")) ? true : false;
    }
}
