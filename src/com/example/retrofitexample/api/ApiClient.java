package com.example.retrofitexample.api;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

import com.example.retrofitexample.api.data.Repo;

public class ApiClient {

	private static ApiClient mInstance;
	private GitHubService mService;

	private ApiClient() {

	}

	private ApiClient(GitHubService service) {
		mService = service;
	}

	public static ApiClient getInstance() {
		if (mInstance == null) {

			RestAdapter restAdapter = new RestAdapter.Builder().setServer(
					"https://api.github.com").build();

			GitHubService service = restAdapter.create(GitHubService.class);

			mInstance = new ApiClient(service);
		}

		return mInstance;
	}

	// https://api.github.com/users/thimes/repos
	public List<Repo> getReposForUser(String user) {
		return mService.listRepos(user);
	}

	public interface GitHubService {
		@GET("/users/{user}/repos")
		List<Repo> listRepos(@Path("user") String user);
	}

}
