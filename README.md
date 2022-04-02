<kbd><img src="https://github.com/ZERDICORP/adv__client/blob/master/screenshots/s1.jpg?row=true" alt="screenshot" width="325" height="350"></kbd>

# adv__client
#### Client for multiplayer game ADV.
## What it is? :eyes:

**ADV** is an abbreviation that stands for.. idk to be honest :sweat_smile:  
Maybe, **ADV**enture, or maybe **A**dult **D**og **V**iolence :dog:  
I thought that I would create an adventure game, hence the acronym. 

I created this game just for fun, I wanted to learn more about sockets and network programming in general.  
As a result, we got such a fun 2D online shooter :gun:

## How can I try it? :hushed:

#### First you need to clone this repository (yes, really!)
```
$ git clone https://github.com/ZERDICORP/adv__client.git
```

#### Next make sure you have java installed
> I tested this project only on version 17.0.3
```
$ java --version
```

#### Next run the following command
> If anyone is interested, we just run the build script in the src folder and go to the build folder
```
$ cd adv__client/src/ && ./build && cd ../build/
```

#### Finally we can run the program with the run script
```
$ ./run
[adv:log] Please specify a server..
```

> Oops, I forgot to clarify something.. since this is an online multiplayer game, we have to tell our client which server he wants to join.  
> So far only one server is available - mine.

```
$ ./run 188.187.188.37:86
```

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
