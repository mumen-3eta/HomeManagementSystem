package graduationproject.homemanagementsystem.dataClasses;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface JsonApi {

    @POST("users/login")
    Call<loginResponse> Login(@Body loginBody body);

    @GET("users/me")
    Call<authMe> authenticate(@Header("cookie") String token);

    @GET("users/profile")
    Call<getProfile> getProfileData(@Header("cookie") String token);

    @POST("users/register")
    Call<loginResponse> register(@Body loginBody body);

    @PUT("users/profile")
    Call<getProfile> updateProfile(@Header("cookie") String token, @Body data profile);

    @GET("controller/connection/all")
    Call<controllerResponse> getAllControllers(@Header("cookie") String token);

    @GET("processor/connection")
    Call<processorResponse> getAllProcessors(@Header("cookie") String token);

    @POST("controller/change")
    Call<changeStatusResponse> changeStatus(@Header("cookie") String token, @Body controllerData controller);

    @GET("controller/type")
    Call<typeResponse> getTypes(@Header("cookie") String token);

    @GET("controller/location")
    Call<locationResponse> getLocations(@Header("cookie") String token);

    @POST("controller/connection")
    Call<controllerResponse> connectController(@Header("cookie") String token, @Body controllerData controller);

    @POST("processor/connection")
    Call<processorResponse> connectProcessor(@Header("cookie") String token, @Body processorData processor);

    @POST("controller/connection/processor")
    Call<controllerResponse> getProcessorControllers(@Header("cookie") String token, @Body processorData processor);

    @GET("users/profile/all")
    Call<getProfileAll> getAllUsers(@Header("cookie") String token);

    @GET("processor/all")
    Call<processorResponseAll> getProcessorsAll(@Header("cookie") String token);

    @GET("controller/all")
    Call<controllerResponseAll> getControllersAll(@Header("cookie") String token);

    @POST("controller/type")
    Call<typeResponseAdmin> addControllerType(@Header("cookie") String token, @Body typeData type);

    @PUT("controller/type")
    Call<typeResponseAdmin> editControllerType(@Header("cookie") String token, @Body typeData type);

    @HTTP(method = "DELETE", path = "controller/type", hasBody = true)
    Call<typeResponseAdmin> deleteControllerType(@Header("cookie") String token, @Body typeData type);

    @POST("controller/location")
    Call<locationResponseAdmin> addControllerLocation(@Header("cookie") String token, @Body locationData location);

    @HTTP(method = "DELETE", path = "controller/location", hasBody = true)
    Call<locationResponseAdmin> deleteControllerLocation(@Header("cookie") String token, @Body locationData location);

    @PUT("controller/location")
    Call<locationResponseAdmin> editControllerLocation(@Header("cookie") String token, @Body locationData location);

    @POST("processor/create")
    Call<processorResponseAdmin> createNewProcessor(@Header("cookie") String token);

    @POST("controller/create")
    Call<controllerResponseAdmin> createNewController(@Header("cookie") String token);

    @HTTP(method = "DELETE", path = "processor/connection", hasBody = true)
    Call<processorResponse> removeProcessor(@Header("cookie") String token, @Body processorData processor);

    @HTTP(method = "DELETE", path = "controller/connection", hasBody = true)
    Call<controllerResponse> removeController(@Header("cookie") String token, @Body controllerData controller);
}
