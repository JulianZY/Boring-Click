@echo off
RunDll32.exe USER32.DLL,UpdatePerUserSystemParameters

title �������汳��
RunDll32.exe USER32.DLL,UpdatePerUserSystemParameters

set rex=reg add "HKEY_CURRENT_USER\Control Panel\Desktop"
%rex% /v TileWallpaper /d "0" /f
%rex% /v Wallpaper /d "D:/Workspaces/MyEclipseProfessional2014/BoringClick/src/Datas/Wallpapers/3.bmp" /f
%rex% /v WallpaperStyle /d "2" /f
echo ���ڸ������汳��
RunDll32.exe USER32.DLL,UpdatePerUserSystemParameters

echo �������汳���ɹ�
RunDll32.exe USER32.DLL,UpdatePerUserSystemParameters

RunDll32.exe USER32.DLL,UpdatePerUserSystemParameters

exit
