@echo off
RunDll32.exe USER32.DLL,UpdatePerUserSystemParameters

title 更换桌面背景
RunDll32.exe USER32.DLL,UpdatePerUserSystemParameters

set rex=reg add "HKEY_CURRENT_USER\Control Panel\Desktop"
%rex% /v TileWallpaper /d "0" /f
%rex% /v Wallpaper /d "D:/Workspaces/MyEclipseProfessional2014/BoringClick/src/Datas/Wallpapers/3.bmp" /f
%rex% /v WallpaperStyle /d "2" /f
echo 正在更换桌面背景
RunDll32.exe USER32.DLL,UpdatePerUserSystemParameters

echo 更换桌面背景成功
RunDll32.exe USER32.DLL,UpdatePerUserSystemParameters

RunDll32.exe USER32.DLL,UpdatePerUserSystemParameters

exit
