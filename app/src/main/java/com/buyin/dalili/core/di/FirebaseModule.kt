package com.buyin.dalili.core.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {



    @Provides
    @Singleton
    fun providerFirebaseDatabase():FirebaseDatabase{
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun providerDatabaseReference(): DatabaseReference {
        return FirebaseDatabase.getInstance().reference
    }


}