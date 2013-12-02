package com.example.retrofitexample;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.retrofitexample.api.ApiClient;
import com.example.retrofitexample.api.data.Repo;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final TextView tvHelloContent = (TextView) findViewById(R.id.hello_content);
		ApiClient.getInstance().getReposForUser("JakeWharton", new Callback<List<Repo>>() {

			@Override
			public void failure(RetrofitError arg0) {
				tvHelloContent.setText(R.string.api_failure);
			}

			@Override
			public void success(List<Repo> arg0, Response arg1) {
				StringBuilder repoListBuilder = new StringBuilder();
				boolean isFirst = true;

				for (Repo repo : arg0) {
					if (isFirst) {
						isFirst = false;
					} else {
						repoListBuilder.append(", ");
					}
					repoListBuilder.append(repo.toString());
				}

				tvHelloContent.setText(repoListBuilder.toString());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
