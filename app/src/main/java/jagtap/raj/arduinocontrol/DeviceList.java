package jagtap.raj.arduinocontrol;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class DeviceList extends AppCompatActivity {

  //widgets
  Button btnPaired;
  ListView deviceList;

  //bluetooth
  private BluetoothAdapter myBluetooth = null;
  private Set<BluetoothDevice> pairedDevices;
  public static String EXTRA_ADDRESS = "device_address";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_device_list);


    //calling widgets
    btnPaired = findViewById(R.id.button);
    deviceList = findViewById(R.id.listView);


    //if device has buetooth
    myBluetooth = BluetoothAdapter.getDefaultAdapter();

    if (myBluetooth == null){
      Toast.makeText(getApplicationContext(),"Bluetooth Device Not Available.",Toast.LENGTH_LONG).show();

      //finish apk
      finish();
    }
    else if (!myBluetooth.isEnabled()){
      Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
      startActivityForResult(turnBTon,1);
    }

    btnPaired.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        pairedDeviceList();
      }
    });
  }

  private void pairedDeviceList(){
    pairedDevices = myBluetooth.getBondedDevices();
    ArrayList list = new ArrayList();

    if (pairedDevices.size()>0){
      for (BluetoothDevice bt:pairedDevices){
        list.add(bt.getName() + "\n" + bt.getAddress()); //get device name and address
      }
    }
    else {
      Toast.makeText(getApplicationContext(),"No Paired Devices Found",Toast.LENGTH_LONG).show();
    }

    final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
    deviceList.setAdapter(adapter);
    deviceList.setOnItemClickListener(myListClickListner);
  }

  private AdapterView.OnItemClickListener myListClickListner = new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
      String info = ((TextView) view).getText().toString();
      String address = info.substring(info.length()-17);



      //make an intent to start next activity
      Intent intent = new Intent(DeviceList.this, ArduinoControl.class);

      //change activity
      intent.putExtra(EXTRA_ADDRESS, address);
      startActivity(intent);
    }
  };

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_device_list, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_guide){
      Intent intent = new Intent(DeviceList.this,GuideActivity.class);
      startActivity(intent);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
