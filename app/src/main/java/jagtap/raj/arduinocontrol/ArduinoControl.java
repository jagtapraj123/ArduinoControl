package jagtap.raj.arduinocontrol;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.UUID;

public class ArduinoControl extends AppCompatActivity {

  Button btnDis;
  ToggleButton pin[] = new ToggleButton[12];
  TextView stat[] = new TextView[12];
  String address = null;
  private ProgressDialog progress;
  BluetoothAdapter myBluetooth = null;
  BluetoothSocket btSocket = null;
  private boolean isBtConnected = false;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent newintent = getIntent();
    address = newintent.getStringExtra(DeviceList.EXTRA_ADDRESS); //receiving address
    setContentView(R.layout.activity_arduino_control);

    //call widgets
    assignIDs();
    assignTexts();

    btnDis.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Disconnect();
      }
    });

    pin[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked){
          sendPinON(0);
        }
        else if (!isChecked){
          sendPinOFF(0);
        }
      }
    });

    pin[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked){
          sendPinON(1);
        }
        else if (!isChecked){
          sendPinOFF(1);
        }
      }
    });

    pin[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked){
          sendPinON(2);
        }
        else if (!isChecked){
          sendPinOFF(2);
        }
      }
    });

    pin[3].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked){
          sendPinON(3);
        }
        else if (!isChecked){
          sendPinOFF(3);
        }
      }
    });

    pin[4].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked){
          sendPinON(4);
        }
        else if (!isChecked){
          sendPinOFF(4);
        }
      }
    });

    pin[5].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked){
          sendPinON(5);
        }
        else if (!isChecked){
          sendPinOFF(5);
        }
      }
    });

    pin[6].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked){
          sendPinON(6);
        }
        else if (!isChecked){
          sendPinOFF(6);
        }
      }
    });

    pin[7].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked){
          sendPinON(7);
        }
        else if (!isChecked){
          sendPinOFF(7);
        }
      }
    });

    pin[8].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked){
          sendPinON(8);
        }
        else if (!isChecked){
          sendPinOFF(8);
        }
      }
    });

    pin[9].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked){
          sendPinON(9);
        }
        else if (!isChecked){
          sendPinOFF(9);
        }
      }
    });

    pin[10].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked){
          sendPinON(10);
        }
        else if (!isChecked){
          sendPinOFF(10);
        }
      }
    });

    pin[11].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked){
          sendPinON(11);
        }
        else if (!isChecked){
          sendPinOFF(11);
        }
      }
    });

    new ConnectBT().execute();
  }

  void assignIDs(){
    btnDis = findViewById(R.id.buttonDIS);

    pin[0] = findViewById(R.id.pin2);
    pin[1] = findViewById(R.id.pin3);
    pin[2] = findViewById(R.id.pin4);
    pin[3] = findViewById(R.id.pin5);
    pin[4] = findViewById(R.id.pin6);
    pin[5] = findViewById(R.id.pin7);
    pin[6] = findViewById(R.id.pin8);
    pin[7] = findViewById(R.id.pin9);
    pin[8] = findViewById(R.id.pin10);
    pin[9] = findViewById(R.id.pin11);
    pin[10] = findViewById(R.id.pin12);
    pin[11] = findViewById(R.id.pin13);

    stat[0] = findViewById(R.id.stat2);
    stat[1] = findViewById(R.id.stat3);
    stat[2] = findViewById(R.id.stat4);
    stat[3] = findViewById(R.id.stat5);
    stat[4] = findViewById(R.id.stat6);
    stat[5] = findViewById(R.id.stat7);
    stat[6] = findViewById(R.id.stat8);
    stat[7] = findViewById(R.id.stat9);
    stat[8] = findViewById(R.id.stat10);
    stat[9] = findViewById(R.id.stat11);
    stat[10] = findViewById(R.id.stat12);
    stat[11] = findViewById(R.id.stat13);
  }

  void assignTexts(){
    for (int i=0;i<12;i++){
      updateText(i,false);
    }
  }

  void updateText(int i, boolean on){
    if (on){
      int pin = i+2;
      String s = "Pin" + pin + ": HIGH";
      stat[i].setText(s);
      stat[i].setTextColor(getResources().getColor(R.color.red));
    }
    else if (!on){
      int pin = i+2;
      String s = "Pin" + pin + ": LOW";
      stat[i].setText(s);
      stat[i].setTextColor(getResources().getColor(R.color.black));
    }
  }

  private class ConnectBT extends AsyncTask<Void, Void, Void> {
    private boolean ConnectSuccess = true;

    @Override
    protected void onPreExecute() {
      progress = ProgressDialog.show(ArduinoControl.this, "Connecting...", "Please Wait...");
    }

    @Override
    protected Void doInBackground(Void... voids) {
      try {
        if (btSocket == null || !isBtConnected){
          myBluetooth = BluetoothAdapter.getDefaultAdapter();
          BluetoothDevice dispositive = myBluetooth.getRemoteDevice(address);
          btSocket = dispositive.createInsecureRfcommSocketToServiceRecord(myUUID);
          BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
          btSocket.connect();
        }
      }
      catch (IOException e){
        ConnectSuccess = false;
      }
      return null;
    }

    @Override
    protected void onPostExecute( Void result) {
      super.onPostExecute(result);

      if (!ConnectSuccess){
        msg("Connection Failed. Is it a SPP Bluetooth? Try again...");
        finish();
      }
      else {
        msg("Connected!");
        isBtConnected = true;
      }
      progress.dismiss();
    }
  }

  private void Disconnect(){
    if (btSocket != null){
      try {
        btSocket.close();
      }
      catch (IOException e){
        msg("Error");
      }
    }
    finish();
  }

  private void sendPinON(int i){
    if (btSocket != null){
      try {
        btSocket.getOutputStream().write(i);
        updateText(i,true);
      }
      catch (IOException e){
        msg("Connection failed.");
      }
    }
  }

  private void sendPinOFF(int i){
    if (btSocket != null){
      try {
        btSocket.getOutputStream().write(26+i);
        updateText(i,false);
      }
      catch (IOException e){
        msg("Connection failed.");
      }
    }
  }

  public void msg(String s){
    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_arduino_control, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_guide){
      Intent intent = new Intent(ArduinoControl.this,GuideActivity.class);
      startActivity(intent);
    }
    return super.onOptionsItemSelected(item);
  }
}