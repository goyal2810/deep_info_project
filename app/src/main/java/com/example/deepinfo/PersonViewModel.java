package com.example.deepinfo;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.AndroidViewModel;

public class PersonViewModel extends AndroidViewModel {

    private PersonDao personDao;
    private PersonDatabase personDatabase;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        personDatabase = PersonDatabase.getDatabase(application);
        personDao = personDatabase.personDao();
    }

    public void insert(PersonInfo personInfo){
        new InsertInfoInAsync(personDao).execute(personInfo);
    }

    private class InsertInfoInAsync extends AsyncTask<PersonInfo, Void, Void> {

        PersonDao personDao;

        public InsertInfoInAsync(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(PersonInfo... personInfos) {
            personDao.insert(personInfos[0]);
            return null;
        }
    }
}
