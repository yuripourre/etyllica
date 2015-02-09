# Etyllica (v 4.5) ![Travis CI Badge](https://api.travis-ci.org/yuripourre/etyllica.svg)

[Etyllica](http://yuripourre.github.com/etyllica) is a pure Java Game Engine to make games fast. It can render 3D (by software) using [jGL](http://www.cmlab.csie.ntu.edu.tw/~robin/jGL/) in unsigned applets and streams audio with [SoundSystem](http://www.paulscode.com/forum/index.php?topic=4.0) Library.

## Features

- Keyboard and Mouse handling (and Multiple Joysticks Handling [for Linux])
- Image Loader that supports (TGA, PCX, PNG, BMP, JPG, GIF [yes, animated])
- True Type Fonts loader (.ttf)
- Animation Tween System
- Animation Pivot System (Used in [Etyllic Animator](http://yuripourre.github.com/etyllic-animator/))
- FullScreen Support (Even in Applets)
- GUI System
- Theme Manager
- Multilanguage Support (GUI changes on the fly)
- Sound Loader that supports (MP3, OGG, WAV)
- Background Music
- Audio Capture
- WaveForm Drawing
- OpenGL simulation with software Rendering (Even in Unsigned Applets)
- Basic Particle System
- Basic Light Map Logic
- Physics (2D)

## Related Projects
### Games
- [Akigawa Training](https://github.com/yuripourre/akigawa) - A Ninja Game
- [Etyllic Pong](http://yuripourre.github.com/etyllic-pong) - Pong Clone
- [Etyllic Mario](http://yuripourre.github.com/etyllic-mario) - Mario Clone
- [Hard to Beat Me](http://yuripourre.github.com/hardtobeatme) - A Game developed at [GGJ 2013](http://globalgamejam.org/)
- [House Defense](https://github.com/yuripourre/childage) - A Game developed at [GGJ 2014](http://globalgamejam.org/)
- [Marvel Quest](https://github.com/yuripourre/marvel-quest) - A beat 'em up game
- [Ninja Robot](https://github.com/yuripourre/ninja-robot) - A casual game
- [Tomb Runaway](https://github.com/yuripourre/runaway) - A maze game

### Non-Games
- [Etyllic Paint](http://yuripourre.github.com/etyllic-paint) - Paint Clone
- [Etyllic Animator](http://yuripourre.github.com/etyllic-animator) - Pivot Animator (2D and 3D)
- [Etyllica Mobile](https://github.com/yuripourre/etyllica-mobile) - Etyllica port to Android.
- [EtyllicTTS](https://github.com/yuripourre/etyllic-tts) - A FreeTTS fork
- [Etyllic GameBoy](https://github.com/yuripourre/etyllic-gameboy) - A [Javaboy](http://www.millstone.demon.co.uk/download/javaboy/) fork with faster rendering.
- [E-Motion](https://github.com/yuripourre/e-motion) - An Image Processing Library made in pure Java.
- [Rubik Solver](https://github.com/UNIRIO-SI/rubik-solver) - A Simple Rubik Cube Application.
- [Veete](https://github.com/yuripourre/veete) - A VEry Easy Tilemap Editor
- [Luvia](https://github.com/yuripourre/luvia) - An Etyllica fork using [JoGL](http://jogamp.org/jogl/www/) and [glg2d](https://github.com/brandonborkholder/glg2d) for GPU rendering.
- [IP-Fun](https://github.com/yuripourre/ip-fun) - An Application to help children to remember IP Addresses.

## Dependencies (optional)

If you don't need 3D Graphics, Sound nor Physics, you just have to include the [etyllica-4.3.jar](https://github.com/yuripourre/etyllica/blob/master/Etyllica/libs/stable/etyllica-4.3.jar) (stable version with 230kB)

- [jGL](http://www.cmlab.csie.ntu.edu.tw/~robin/jGL/) (An OpenGL simulator)
- [SoundSystem](http://www.paulscode.com/forum/index.php?topic=4.0) (Audio Library)
- [WAV, JOgg, JOrbis and MP3 codec plug-in](http://www.paulscode.com/forum/index.php?topic=496.0)
- [dyn4j](http://code.google.com/p/dyn4j/) (Physics Library)

## How to Start
You can follow the [HelloWorld](https://github.com/yuripourre/etyllica/wiki/Hello-World) example.

## Maven Integration
```
<dependency>
  <groupId>br.com.etyllica</groupId>
  <artifactId>etyllica</artifactId>
  <version>4.5</version>
</dependency>
```

##License
- [LGPL](http://www.gnu.org/copyleft/lesser.html)
