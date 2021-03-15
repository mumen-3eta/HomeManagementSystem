package graduationproject.homemanagementsystem.dataClasses;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonApi {

    @POST("users/login")
    Call Login(@Body LoginBody loginBody);
}
