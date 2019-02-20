package jagtap.raj.arduinocontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GuideActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_guide);

    TextView guide1TextView = findViewById(R.id.guideToLink);
    TextView guide2TextView = findViewById(R.id.guideUpload);

    TextView linkTextView = findViewById(R.id.link);
    linkTextView.setClickable(true);
    linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

    String guide1Txt = "Please download the code provided in the following link." + "\n";
    String link = "<a href='https://gist.github.com/jagtapraj123/96064df6838ee3cc5756abc0a14868ee'>Arduino_Controller.ino</a>";
    String guide2Txt = "\n" +
            "Upload the downloaded code on your Arduino." +
            "\n\n" +
            "Connect Rx of bluetooth module to Tx of Arduino and Tx of bluetooth module to Rx of Arduino." +
            "\n\n" +
            "Connect 5V and Ground of bluetooth to 5V and Ground of Arduino." +
            "\n\n" +
            "And you are good to go." +
            "\n\n" +
            "Thank you very much for using this app.";

    guide1TextView.setText(guide1Txt);
    linkTextView.setText(Html.fromHtml(link));
    guide2TextView.setText(guide2Txt);

    guide1TextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
    linkTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
    guide2TextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
  }
}
