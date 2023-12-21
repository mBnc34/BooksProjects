package es.usj.booksprojects.serverOperations.callback;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import es.usj.booksprojects.mapper.AuthorMapper;
import es.usj.booksprojects.model.Author;
import es.usj.booksprojects.serverOperations.valueApi.AuthorValueApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface AuthorGetRequestCallback extends Callback<AuthorValueApi> {

    void onSuccess(Author author);
    void onFailure(Throwable t);

    @Override
    default void onResponse(Call<AuthorValueApi> call, Response<AuthorValueApi> response) {
        if(response.isSuccessful()){
            AuthorValueApi authorValueApi = response.body();
            if(authorValueApi != null){
                Author author = AuthorMapper.toDomainAuthor(authorValueApi);
                onSuccess(author);
            }
        } else {
            onFailure(new Exception("Request failed: " + response.code()));
        }
    }

    @Override
    default void onFailure(@NonNull Call<AuthorValueApi> call, Throwable t) {
        onFailure(t);
    }
}
