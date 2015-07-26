import java.applet.Applet;
import java.io.*;
import sun.audio.*;
import java.net.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioClip
{
    public static void main(String[] args) 
    throws Exception
    {
        URL url = new URL("http://download.wavetlan.com/SVV/Media/HTTP/WAV/Media-Convert/Media-Convert_test7_PCM_Stereo_VBR_16SS_22050Hz.wav");
        url.openConnection();
        AudioStream as = new AudioStream(url.openStream());
        AudioPlayer.player.start(as);
    }

    public static void playGood() 
    throws Exception
    {
        // open the sound file as a Java input stream
        String gongFile = "Mario_Jumping-Mike_Koenig-989896458.wav";
        InputStream in = new FileInputStream(gongFile);

        // create an audiostream from the inputstream
        AudioStream audioStream = new AudioStream(in);

        // play the audio clip with the audioplayer class
        AudioPlayer.player.start(audioStream);
    }

    public static void playFryingPan() 
    throws Exception
    {
        // open the sound file as a Java input stream
        String gongFile = "Frying Pan Impact-SoundBible.com-786709826.wav";
        InputStream in = new FileInputStream(gongFile);

        // create an audiostream from the inputstream
        AudioStream audioStream = new AudioStream(in);

        // play the audio clip with the audioplayer class
        AudioPlayer.player.start(audioStream);
    }
    
    public static void playUpper() 
    throws Exception
    {
        // open the sound file as a Java input stream
        String gongFile = "Upper Cut-SoundBible.com-1272257235.wav";
        InputStream in = new FileInputStream(gongFile);

        // create an audiostream from the inputstream
        AudioStream audioStream = new AudioStream(in);

        // play the audio clip with the audioplayer class
        AudioPlayer.player.start(audioStream);
    }

    public static void playBomb() 
    throws Exception
    {
        // open the sound file as a Java input stream
        String gongFile = "Depth Charge Short-SoundBible.com-1303947570.wav";
        InputStream in = new FileInputStream(gongFile);

        // create an audiostream from the inputstream
        AudioStream audioStream = new AudioStream(in);

        // play the audio clip with the audioplayer class
        AudioPlayer.player.start(audioStream);
    }
    
    public static void playBack() 
    throws Exception 
    {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("Sandstorm_Ringtone.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(inputStream);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);              
    }
}
