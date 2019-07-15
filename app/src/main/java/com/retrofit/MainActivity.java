package com.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.facebook.shimmer.ShimmerFrameLayout;

public class MainActivity extends AppCompatActivity implements Login.OnLoginFormActivityListener {
    ListView listView;
    private ShimmerFrameLayout mShimmerViewContainer;

    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        prefConfig = new PrefConfig(this);
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);

        if (findViewById(R.id.fragment_con) != null) {

            if (savedInstanceState != null) {
                return;
            }
            if (prefConfig.readLoginStatus()) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_con, new Welcome()).commit();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_con, new Login()).commit();
            }
        }



        /*mShimmerViewContainer = findViewById(R.id.shimmer_view_container);


            listView = (ListView) findViewById(R.id.listViewHeroes);

            //calling the method to display the heroes
            getHeroes();

        }*/

       /* private void getHeroes() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                    .build();

            Api api = retrofit.create(Api.class);

            Call<List<Hero>> call = api.getHeroes();

            call.enqueue(new Callback<List<Hero>>() {
                @Override
                public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                    List<Hero> heroList = response.body();

                    //Creating an String array for the ListView
                    String[] heroes = new String[heroList.size()];

                    //looping through all the heroes and inserting the names inside the string array
                    for (int i = 0; i < heroList.size(); i++) {
                        heroes[i] = heroList.get(i).getName();
                    }
                    mShimmerViewContainer.stopShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);


                    //displaying the string array into listview
                    listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));

                }

                @Override
                public void onFailure(Call<List<Hero>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }*/
   /* @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }*/
    }

    @Override
    public void performRegister() {
         getSupportFragmentManager().beginTransaction().replace(R.id.fragment_con, new Register()).commit();

    }

    @Override
    public void performLogin(String name) {

        prefConfig.writeName(name);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_con, new Welcome()).commit();

    }
}
