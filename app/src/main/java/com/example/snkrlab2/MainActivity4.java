package com.example.snkrlab2;

<<<<<<< Updated upstream
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;
import com.snap.camerakit.support.app.CameraActivity;

public class MainActivity4 extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String[] LENS_GROUP_IDS = {"eyJhbGciOiJIUzI1NiIsImtpZCI6IkNhbnZhc1MyU0hNQUNQcm9kIiwidHlwIjoiSldUIn0.eyJhdWQiOiJjYW52YXMtY2FudmFzYXBpIiwiaXNzIjoiY2FudmFzLXMyc3Rva2VuIiwibmJmIjoxNzAyOTcxMTUyLCJzdWIiOiIyYzViOTE0ZC1jZWFmLTQ5MWItOWI1Zi05YTAyOWM1NjRmZTV-U1RBR0lOR35lMDYwZjhiMy1iMDY0LTQyMDItOGFjZS0xNWJjOTVhZmIzZTkifQ.NwIdQqWoSqHRz2vG-uol68eBL4IuYthEoYbU1VEgcGc"};
    private static final String APPLY_LENS_BY_ID = "43296900875";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        TextView captureResultLabel = findViewById(R.id.label_capture_result);
        ImageView imageView = findViewById(R.id.image_preview);
        VideoView videoView = findViewById(R.id.video_preview);
        videoView.setOnPreparedListener(mediaPlayer -> mediaPlayer.isLooping = true);

        View[] mediaPreviews = {videoView, imageView};
        View.OnClickListener buttonClickListener = v -> {
            for (View preview : mediaPreviews) {
                preview.setVisibility(View.GONE);
            }
        };

        Button[] captureButtons = {
                findViewById(R.id.button_capture_lenses),
                findViewById(R.id.button_capture_lenses_wo_control_strip),
                findViewById(R.id.button_capture_lenses_apply_by_id),
                findViewById(R.id.button_capture_lenses_prefetch_all),
                findViewById(R.id.button_capture_lenses_idle_state),
                findViewById(R.id.button_capture_lenses_camera_facing_back),
                findViewById(R.id.button_capture_lens),
                findViewById(R.id.button_capture_lens_wo_control_strip),
                findViewById(R.id.button_capture_lens_no_icon)
        };

        for (Button button : captureButtons) {
            button.setOnClickListener(buttonClickListener);
        }

        Button[] playButtons = {
                findViewById(R.id.button_play_lenses),
                findViewById(R.id.button_play_lens)
        };

        for (Button button : playButtons) {
            button.setOnClickListener(buttonClickListener);
        }

        ComponentActivity componentActivity = this;

        CameraActivity.CaptureContract captureContract = new CameraActivity.CaptureContract();
        componentActivity.registerForActivityResult(captureContract, result -> {
            Log.d(TAG, "Got capture result: " + result);
            switch (result.getClass().getSimpleName()) {
                case "SuccessVideo":
                    CameraActivity.Capture.Result.Success.Video videoResult = (CameraActivity.Capture.Result.Success.Video) result;
                    videoView.setVisibility(View.VISIBLE);
                    videoView.setVideoURI(videoResult.getUri());
                    videoView.start();
                    imageView.setVisibility(View.GONE);
                    captureResultLabel.setText("");
                    break;
                case "SuccessImage":
                    CameraActivity.Capture.Result.Success.Image imageResult = (CameraActivity.Capture.Result.Success.Image) result;
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageURI(imageResult.getUri());
                    videoView.setVisibility(View.GONE);
                    captureResultLabel.setText("");
                    break;
                case "Cancelled":
                    captureResultLabel.setText(getString(R.string.label_result_none));
                    buttonClickListener.onClick(null);
                    break;
                case "Failure":
                    CameraActivity.Capture.Result.Failure failureResult = (CameraActivity.Capture.Result.Failure) result;
                    captureResultLabel.setText(getString(R.string.label_result_failure, failureResult.getException().toString()));
                    buttonClickListener.onClick(null);
                    break;
            }
        });

        CameraActivity.PlayContract playContract = new CameraActivity.PlayContract();
        componentActivity.registerForActivityResult(playContract, result -> {
            Log.d(TAG, "Got play result: " + result);
            switch (result.getClass().getSimpleName()) {
                case "Completed":
                    captureResultLabel.setText(getString(R.string.label_result_completed));
                    buttonClickListener.onClick(null);
                    break;
                case "Failure":
                    CameraActivity.Play.Result.Failure playFailureResult = (CameraActivity.Play.Result.Failure) result;
                    captureResultLabel.setText(getString(R.string.label_result_failure, playFailureResult.getException().toString()));
                    buttonClickListener.onClick(null);
                    break;
            }
        });

        findViewById(R.id.button_capture_lenses).setOnClickListener(v ->
                captureContract.launch(new CameraActivity.Capture.Params.WithLenses.Builder().lensGroupIds(LENS_GROUP_IDS).build()));
        findViewById(R.id.button_capture_lenses_wo_control_strip).setOnClickListener(v ->
                captureContract.launch(new CameraActivity.Capture.Params.WithLenses.Builder().lensGroupIds(LENS_GROUP_IDS)
                        .cameraAdjustmentsConfiguration(new CameraActivity.Capture.Params.CameraAdjustmentsConfiguration.Builder()
                                .toneAdjustmentEnabled(false)
                                .portraitAdjustmentEnabled(false)
                                .build())
                        .cameraFacingBasedOnLens(true)
                        .cameraFacingFlipEnabled(false)
                        .cameraFlashEnabled(false)
                        .build()));
        findViewById(R.id.button_capture_lenses_apply_by_id).setOnClickListener(v ->
                captureContract.launch(new CameraActivity.Capture.Params.WithLenses.Builder().lensGroupIds(LENS_GROUP_IDS)
                        .applyLensById(APPLY_LENS_BY_ID)
                        .build()));
        findViewById(R.id.button_capture_lenses_prefetch_all).setOnClickListener(v ->
                captureContract.launch(new CameraActivity.Capture.Params.WithLenses.Builder().lensGroupIds(LENS_GROUP_IDS)
                        .prefetchLensByIdPattern("\\S+")
                        .build()));
        findViewById(R.id.button_capture_lenses_idle_state).setOnClickListener(v ->
                captureContract.launch(new CameraActivity.Capture.Params.WithLenses.Builder().lensGroupIds(LENS_GROUP_IDS)
                        .disableIdleState(false)
                        .build()));
        findViewById(R.id.button_capture_lenses_camera_facing_back).setOnClickListener(v ->
                captureContract.launch(new CameraActivity.Capture.Params.WithLenses.Builder().lensGroupIds(LENS_GROUP_IDS)
                        .applyLensById(APPLY_LENS_BY_ID)
                        .cameraFacingFront(false)
                        .build()));
        findViewById(R.id.button_capture_lens).setOnClickListener(v ->
                captureContract.launch(new CameraActivity.Capture.Params.WithLens.Builder().lensGroupId(LENS_GROUP_IDS[0])
                        .lensId(APPLY_LENS_BY_ID)
                        .build()));
        findViewById(R.id.button_capture_lens_wo_control_strip).setOnClickListener(v ->
                captureContract.launch(new CameraActivity.Capture.Params.WithLens.Builder().lensGroupId(LENS_GROUP_IDS[0])
                        .lensId(APPLY_LENS_BY_ID)
                        .cameraAdjustmentsConfiguration(new CameraActivity.Capture.Params.CameraAdjustmentsConfiguration.Builder()
                                .toneAdjustmentEnabled(false)
                                .portraitAdjustmentEnabled(false)
                                .build())
                        .cameraFacingFront(true)
                        .cameraFacingFlipEnabled(false)
                        .cameraFlashEnabled(false)
                        .build()));
        findViewById(R.id.button_capture_lens_no_icon).setOnClickListener(v ->
                captureContract.launch(new CameraActivity.Capture.Params.WithLens.Builder().lensGroupId(LENS_GROUP_IDS[0])
                        .lensId(APPLY_LENS_BY_ID)
                        .displayLensIcon(false)
                        .build()));
        findViewById(R.id.button_play_lenses).setOnClickListener(v ->
                playContract.launch(new CameraActivity.Play.Params.WithLenses.Builder().lensGroupIds(LENS_GROUP_IDS).build()));
        findViewById(R.id.button_play_lens).setOnClickListener(v ->
                playContract.launch(new CameraActivity.Play.Params.WithLens.Builder().lensGroupId(LENS_GROUP_IDS[0])
                        .lensId(APPLY_LENS_BY_ID)
                        .build()));
=======
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity4 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
>>>>>>> Stashed changes
    }
}
