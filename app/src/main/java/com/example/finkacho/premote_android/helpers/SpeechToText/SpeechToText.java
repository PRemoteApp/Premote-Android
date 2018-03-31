package com.example.finkacho.premote_android.helpers.SpeechToText;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import com.example.finkacho.premote_android.R;

import java.util.List;

public class SpeechToText implements RecognitionListener {

    private Context context;
    private SpeechRecognizer sr;
    private OnSuccessRecognition onSuccess;
    private OnRecognitionFailure onFail;
    private BeforeRecognitionStart before;

    public SpeechToText(Context context){
        this.context = context;
        sr = SpeechRecognizer.createSpeechRecognizer(context);
        sr.setRecognitionListener(this);
    }

    public void start(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,context.getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,5);
        sr.startListening(intent);
    }

    public SpeechToText onSuccess(OnSuccessRecognition listener){
        this.onSuccess = listener;
        return this;
    }

    public SpeechToText onFail(OnRecognitionFailure listener){
        this.onFail = listener;
        return this;
    }

    public SpeechToText before(BeforeRecognitionStart listener){
        this.before = listener;
        return this;
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        if(before==null)return;
        before.Before();
    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int error) {
        if(onFail!=null){
            onFail.onFailure("Sorry can't handle error now");
        }
        Log.d("Errpr", "ERRRRRRRRRRRRRRRPRRRRRRRRRRRRRRR");
    }

    @Override
    public void onResults(Bundle results) {
        if(onSuccess!=null){
            List<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            if(data!=null) {
                onSuccess.onSuccess(data.get(0));
            }else{
                onFail.onFailure(context.getResources().getString(R.string.unknownError));
            }
        }
        Log.d("Errpr", "VAJAAAAAAA");
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}
