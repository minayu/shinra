package jp.ac.jec.cm0130.genju;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChattelActivity extends AppCompatActivity {
    private ArrayList<Chattel> ary = new ArrayList<>();

    private ChattelItemAdapter adapter = null;

    class ChattelItemAdapter extends ArrayAdapter<Chattel> {
        ChattelItemAdapter(Context context) {
            super(context, R.layout.chattel_item);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Chattel item = getItem(position);
            if (convertView == null) {
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.chattel_item, null);
            }
            if (item != null) {
                TextView txtChattelName = (TextView) convertView.findViewById(R.id.chattelName);
                if (txtChattelName != null) {
                    txtChattelName.setText(item.getName());
                }
                TextView txtChattelCount = (TextView) convertView.findViewById(R.id.chattelCount);
                if (txtChattelCount != null) {
                    txtChattelCount.setText(String.valueOf(item.getCount()));
                }
//                ImageButton ibtn = (ImageButton) convertView.findViewById(R.id.useButton);
            }
            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        ChattelSQLiteOpenHelper helper = new ChattelSQLiteOpenHelper(this);
        helper.getAllChattelItem(ary);

        adapter = new ChattelItemAdapter(this);
        adapter.addAll(ary);

        ListView list = (ListView)findViewById(R.id.list_chattel);
        list.setAdapter(adapter);

//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ListView list = (ListView) parent;
//                Drop item = (Drop) list.getItemAtPosition(position);
//                String idStr = String.valueOf(item.getId());
////                    Toast.makeText(MoneyListActivity.this, idStr, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(DropListActivity.this, AddMoneyActivity.class);
//                intent.putExtra("id", idStr);
//                startActivity(intent);
//            }
//        });

        findViewById(R.id.iButtonHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//          FLAG_ACTIVITY_CLEAR_TOPは、
//          遷移先のアクティビティが既に動いていればそのアクティビティより上にある
//          （この場合はB, C, D）アクティビティを消す、という挙動を設定する。
//          これによって、A→B→C→D→Aと遷移した後にbackボタンを押してもDに戻ることはなくなる。
                Intent intent = new Intent(ChattelActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        findViewById(R.id.iButtonDungeon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChattelActivity.this, DungeonActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        findViewById(R.id.iButtonShop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChattelActivity.this, ShopActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        findViewById(R.id.iButtonEnemy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChattelActivity.this, EnemyActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter = new ChattelItemAdapter(this);
        adapter.addAll(ary);

        ListView list = (ListView)findViewById(R.id.list_chattel);
        list.setAdapter(adapter);
    }
}
