package com.fns.examprep
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


object RetrofitClient {
        private const val BASE_URL="http://192.168.1.6:80/"
        val instance :Apiservice by lazy{
            retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Apiservice::class.java)

        }
        public static List<T> retrofit<T>(Retrofit dt)
        {
            var columnNames = dt.Columns.Cast<DataColumn>().Select(c => c.ColumnName.ToLower()).ToList();
            var properties = typeof(T).GetProperties();
            return dt.AsEnumerable().Select(row => {
                var objT = Activator.CreateInstance<T>();
                foreach (var pro in properties)
                {
                    if (columnNames.Contains(pro.Name.ToLower()))
                    {
                        try
                        {
                            pro.SetValue(objT, row[pro.Name]);
                        }
                        catch (Exception ex) { }
                    }
                }
                return objT;
            }).ToList();
        }
        @FormUrlEncoded
        @POST("api/RetrofitModel")
        fun insertRegisterUser(@FieldMap param:HashMap<String?,String?>): Call<RetrofitModel>

    public class RetrofitResponse
    {
        public List<retrofit> rcList { get; set; }
    }
    public class RetrofitReturn
    {
        public RetrofitStatus status { get; set; }
        public RetrofitResponse RetrofitResult { get; set; }
        public RetrofitReturn()
        {
            Retrofitstatus = new Status();
            RetrofitResult = new RetrofitResponse();
        }

    }

}



