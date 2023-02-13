# adv__client :space_invader:
#### Client for multiplayer game ADV.
## What it is? :eyes:

**ADV** is an abbreviation that stands for.. idk to be honest :sweat_smile:  
Maybe, **ADV**enture, or maybe **A**dult **D**og **V**iolence.. :dog:  
I thought that I would create an adventure game, hence the acronym. 

I created this game just for fun, I wanted to learn more about sockets and network programming in general.  
As a result, we got such a fun 2D online shooter :gun:

## How can I try it? :hushed:

#### First you need to clone this repository (yes, really!)
```
$ git clone https://github.com/ZERDICORP/adv__client.git
```

#### Next make sure you have java & jar installed
```
$ java --version
openjdk 17.0.3 2022-04-19
OpenJDK Runtime Environment (build 17.0.3+3)
OpenJDK 64-Bit Server VM (build 17.0.3+3, mixed mode)
$ jar --version
jar 17.0.3
```

#### Next run the following command
```
$ cd adv__client/src/ && ./build && cd ../build/
```
<details>
  <summary>windows user?</summary>
  
  ```
  > cd adv__client\src\ && win_build.bat && cd ..\build
  ```
</details>

#### Finally we can run the program with the run script
```
$ ./run
[adv:log] Please specify a server..
```
<details>
  <summary>windows user?</summary>
  
  ```
  > win_run.bat
  [adv:log] Please specify a server..
  ```
</details>

**Oops, I forgot to clarify something.. since this is an online multiplayer game, we have to tell our client which server he wants to join.  
So far only one server is available - mine.**

```
$ ./run 95.165.89.228:86
```
<details>
  <summary>windows user?</summary>
  
  ```
  > win_run.bat 95.165.89.228:86
  ```
</details>

#### Hooray!

## How to play it? :rage1:

**W** - _move up_  
**A** - _move left_  
**S** - _move down_  
**D** - _move right_  

> Important!  
> With the first press, you turn the player's gaze, and only with the second - move forward.

**E** - _take a shot_

> Cartridges can be seen around the player

**F** - _set a block_

> Blocks can only be placed in the central square

**Interesting facts:**

+ The game follows the classic "wave" scenario.  
+ At the bottom of the screen, you can see the "new wave" indicator, which means the time until the next wave starts in seconds.  
+ Before the onset of the wave, you need to build a shelter.  
+ The number of blocks is limited (this can also be seen at the bottom of the screen).  
+ Blocks will be restored after the end of the wave.
+ Your player model is shaded.

## Bad news :pensive:

If your screen height is less than 800 pixels, the picture will break.  
You will be able to play, but the bottom of the game window will simply float under the screen.  
  
**Don't hit me hard!  
I'm not a frontender!  
It's not my thing to adjust pixels! :triumph:**

## Screenshot
![image](https://user-images.githubusercontent.com/56264511/161407898-af69908b-691a-4d97-8bc5-1c266e4e6049.png)
