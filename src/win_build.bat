@echo off

javac -cp . com\adv_client\www\Main.java
  
md ..\build
  
jar -cfe ..\build\adv__client.jar com.adv__client.www.Main .

echo @echo off > ..\build\win_run.bat
echo java -cp adv__client.jar com.adv_client.www.Main %%* >> ..\build\win_run.bat
