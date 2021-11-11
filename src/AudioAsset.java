import ky.Asset;
import ky.AudioPlayer;
import ky.Vector2D;

public class AudioAsset extends Asset {

    private AudioPlayer audio;
    private int lastIndex;

    public AudioAsset(String audioFile, String[] assets, Vector2D position) {
        super(assets, position, 2);
        lastIndex = getImages().length-1;
        setImageIndex(lastIndex);
        audio = new AudioPlayer(audioFile);
    }

    public void decreaseVolume () {
        if (getImageIndex() > 0) {
            setImageIndex(getImageIndex()-1);
            audio.setVolume(audio.getVolume()-8);
            if (getImageIndex()==0) {
                audio.setVolume(-80);
            }
            lastIndex--;
        }
    }

    public void increaseVolume () {
        if (getImageIndex() < getImages().length-1) {
            if (audio.getVolume() == -80) {
                audio.setVolume(-8);
            }
            lastIndex++;
            setImageIndex(getImageIndex()+1);
            audio.setVolume(audio.getVolume()+8);
        }
    }

    public void mute () {
        if (audio.getVolume() == -80) {
            audio.play();
            audio.setVolume(lastIndex*-8 + 8);
            setImageIndex(1);
        } else {
            audio.setVolume(-80);
            setImageIndex(0);
        }
    }

    public AudioPlayer getAudioPlayer () {
        return audio;
    }
    
}
