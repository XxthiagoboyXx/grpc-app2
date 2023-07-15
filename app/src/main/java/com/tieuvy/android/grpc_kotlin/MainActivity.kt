package com.tieuvy.android.grpc_kotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tieuvy.android.grpc_kotlin.databinding.ActivityMainBinding
import io.grpc.ManagedChannelBuilder
import java.util.Calendar

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        joinRoom()
    }

    fun getChanel() = ManagedChannelBuilder.forTarget("192.168.53.103:50051").usePlaintext().build()

    fun joinRoom() {
        try {
            val stub = ChatGrpc.newBlockingStub(getChanel())
            val request = JoinRoomRequest.newBuilder().setJoinAt(Calendar.getInstance().timeInMillis)
                .setUserInfo(UserInfo.newBuilder().setId("1").setName("Vy HUng").build()).build()
            val response = stub.joinRoom(request)
            Log.e(TAG, "joinRoom: ${response.userInfoListList.map { it.name }.toString() }}", )
        }catch (e: Exception){
            Log.e(TAG, "joinRoom: ${e.message}", )
        }

    }
}