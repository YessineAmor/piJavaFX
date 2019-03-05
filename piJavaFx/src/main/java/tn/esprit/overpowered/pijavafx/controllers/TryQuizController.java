/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.pijavafx.controllers;

import com.github.sarxos.webcam.Webcam;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.openimaj.image.processing.face.detection.DetectedFace;
import org.openimaj.image.processing.face.detection.HaarCascadeDetector;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.video.ConverterFactory;
import com.xuggle.xuggler.video.IConverter;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Date;
import org.openimaj.image.ImageUtilities;
import org.openimaj.math.geometry.shape.Rectangle;
import tn.esprit.overpowered.byusforus.entities.quiz.QuizTry;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class TryQuizController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    private Boolean stopCamera = false;
    private BufferedImage grabbedImage;
    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();
    private List<DetectedFace> faces = null;
    private Webcam webcam = null;
    private String mediaPath = "../media/";
    BufferedImage capture = null;
    List<BufferedImage> facesList = new ArrayList<>();
    private IMediaWriter writer;
    @FXML
    private ImageView videoArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("alert accepted - TryQuizController");
        QuizTry quizTry = new QuizTry();
        long start = System.currentTimeMillis();
        quizTry.setStartDate(new Date());
        initVideo(quizTry.getRecording());
        initCam();
        Button stopCapture = new Button("Stop capture");
        anchorPane.getChildren().add(stopCapture);
        Thread cameraThread = new Thread(() -> {
            while (!stopCamera) {
                capture = webcam.getImage();
                detectFaces(capture);
                Image img = SwingFXUtils.toFXImage(capture, null);
                imageProperty.set(img);
                saveCaptureToVideo(capture, start);
                System.out.println("Detected " + faces.size() + " faces");

            }

        });

        cameraThread.start();
        videoArea.imageProperty().bind(imageProperty);
        stopCapture.setOnAction((e) -> {
            stopCamera = true;
            writer.close();
            webcam.close();
        });

    }

    public void detectFaces(BufferedImage imageToAnalyse) {
        HaarCascadeDetector detector = new HaarCascadeDetector();
        faces = detector.detectFaces(ImageUtilities.createFImage(imageToAnalyse));
        facesList.clear();
        for (DetectedFace face : faces) {
            BufferedImage facePatch = ImageUtilities.createBufferedImage(face.getFacePatch());
            facesList.add(facePatch);
            Rectangle bounds = face.getBounds();
            int dx = (int) (0.1 * bounds.width);
            int dy = (int) (0.2 * bounds.height);
            int x = (int) bounds.x - dx;
            int y = (int) bounds.y - dy;
            int w = (int) bounds.width + 2 * dx;
            int h = (int) bounds.height + dy;
            Graphics g = capture.getGraphics();
            g.drawRect(x, y, w, h);
        }
    }

    public void initCam() {
        webcam = Webcam.getWebcams().get(1);
        webcam.setViewSize(new Dimension(640, 480));
        webcam.open();
    }

    public void initVideo(String fileName) {
        File file = new File(fileName);
        writer = ToolFactory.makeWriter(mediaPath + file.getName());
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, 640, 480);
    }

    private void saveCaptureToVideo(BufferedImage imageToSave, long start) {
        BufferedImage videoImage = ConverterFactory.convertToType(imageToSave, BufferedImage.TYPE_3BYTE_BGR);
        IConverter converter = ConverterFactory.createConverter(videoImage, IPixelFormat.Type.YUV420P);
        IVideoPicture frame = converter.toPicture(videoImage, (System.currentTimeMillis() - start) * 1000);
        writer.encodeVideo(0, frame);
    }

}
