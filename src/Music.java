import ky.Asset;
import ky.AudioPlayer;
import ky.Vector2D;

public class Music extends Asset {

    private AudioPlayer musicPlayer;

    public Music() {
        super(new String[]{
            "assets/sound-1.png",
            "assets/sound0.png", 
            "assets/sound1.png", 
            "assets/sound2.png"},
            new Vector2D((1920-75/2)/1.25, (75/2)/1.25), 2);
        setImageIndex(3);
        musicPlayer = new AudioPlayer("assets/Chopin - Nocturne op9 No2.wav");
        musicPlayer.setTime(0, 2, 0);
        musicPlayer.setLoop(true);
        musicPlayer.play();
    }

    public void decreaseVolume () {
        if (getImageIndex() > 0) {
            setImageIndex(getImageIndex()-1);
            musicPlayer.setVolume(musicPlayer.getVolume()-8);
            if (getImageIndex()==0) {
                musicPlayer.setVolume(-80);
            }
        }
    }

    public void increaseVolume () {
        if (getImageIndex() < 3) {
            if (musicPlayer.getVolume() == -80) {
                musicPlayer.setVolume(-8);
            }
            setImageIndex(getImageIndex()+1);
            musicPlayer.setVolume(musicPlayer.getVolume()+8);
        }
    }

    public void mute () {
        if (musicPlayer.getVolume() == -80) {
            musicPlayer.play();
            musicPlayer.setVolume(-8*3);
            setImageIndex(1);
        } else {
            musicPlayer.setVolume(-80);
            setImageIndex(0);
        }
    }

    public AudioPlayer getMusicPlayer () {
        return musicPlayer;
    }
    
}
