@echo off
setlocal
set HALSIM_EXTENSIONS=C:\Users\auror\Documents\GitHub\FRC-2020\Characterization\characterization-project\build\tmp\expandedArchives\halsim_gui-2021.1.2-windowsx86-64.zip_a65f029882460810c7d9fdb675bf2f07\windows\x86-64\shared\halsim_gui.dll
set PATH=C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v11.2\bin;C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v11.2\libnvvp;C:\Program Files\National Instruments\Shared\OpenVINO\;C:\Program Files (x86)\Common Files\Oracle\Java\javapath;C:\Program Files (x86)\Intel\iCLS Client\;C:\Program Files\Intel\iCLS Client\;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\Intel\Intel(R) Management Engine Components\DAL;C:\Program Files\Intel\Intel(R) Management Engine Components\DAL;C:\Program Files (x86)\Intel\Intel(R) Management Engine Components\IPT;C:\Program Files\Intel\Intel(R) Management Engine Components\IPT;C:\Program Files\Intel\WiFi\bin\;C:\Program Files\Common Files\Intel\WirelessCommon\;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\GtkSharp\2.12\bin;C:\WINDOWS\System32\OpenSSH\;C:\Program Files (x86)\IVI Foundation\VISA\WinNT\Bin\;C:\Program Files\IVI Foundation\VISA\Win64\Bin\;C:\Program Files (x86)\IVI Foundation\VISA\WinNT\Bin;C:\Program Files\PuTTY\;C:\Program Files\Java\jdk-13.0.1\bin;C:\Users\Public\wpilib\2020\roborio\bin;C:\Users\Public\wpilib\2020\frccode;C:\Program Files\Git\cmd;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\WINDOWS\System32\OpenSSH\;C:\Program Files\Microsoft SQL Server\Client SDK\ODBC\110\Tools\Binn\;C:\Program Files (x86)\Microsoft SQL Server\120\Tools\Binn\;C:\Program Files\Microsoft SQL Server\120\Tools\Binn\;C:\Program Files\Microsoft SQL Server\120\DTS\Binn\;C:\Program Files (x86)\Windows Kits\8.1\Windows Performance Toolkit\;D:\MatLab\bin;C:\Program Files\NVIDIA Corporation\Nsight Compute 2020.3.0\;C:\Program Files (x86)\NVIDIA Corporation\PhysX\Common;C:\Program Files\NVIDIA Corporation\NVIDIA NvDLISR;C:\Users\auror\AppData\Local\Programs\Python\Python37-32\Scripts\;C:\Users\auror\AppData\Local\Programs\Python\Python37-32\;C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v11.2\bin;C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v11.2\libnvvp;C:\Program Files\National Instruments\Shared\OpenVINO\;C:\Program Files (x86)\Common Files\Oracle\Java\javapath;C:\Program Files (x86)\Intel\iCLS Client\;C:\Program Files\Intel\iCLS Client\;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\Intel\Intel(R) Management Engine Components\DAL;C:\Program Files\Intel\Intel(R) Management Engine Components\DAL;C:\Program Files (x86)\Intel\Intel(R) Management Engine Components\IPT;C:\Program Files\Intel\Intel(R) Management Engine Components\IPT;C:\Program Files\Intel\WiFi\bin\;C:\Program Files\Common Files\Intel\WirelessCommon\;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\GtkSharp\2.12\bin;C:\WINDOWS\System32\OpenSSH\;C:\Program Files (x86)\IVI Foundation\VISA\WinNT\Bin\;C:\Program Files\IVI Foundation\VISA\Win64\Bin\;C:\Program Files (x86)\IVI Foundation\VISA\WinNT\Bin;C:\Program Files\PuTTY\;C:\Program Files\Java\jdk-13.0.1\bin;C:\Users\Public\wpilib\2020\roborio\bin;C:\Users\Public\wpilib\2020\frccode;C:\Program Files\Git\cmd;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\WINDOWS\System32\OpenSSH\;C:\Program Files\Microsoft SQL Server\Client SDK\ODBC\110\Tools\Binn\;C:\Program Files (x86)\Microsoft SQL Server\120\Tools\Binn\;C:\Program Files\Microsoft SQL Server\120\Tools\Binn\;C:\Program Files\Microsoft SQL Server\120\DTS\Binn\;C:\Program Files (x86)\Windows Kits\8.1\Windows Performance Toolkit\;D:\MatLab\bin;C:\Program Files\NVIDIA Corporation\Nsight Compute 2020.3.0\;C:\Program Files (x86)\NVIDIA Corporation\PhysX\Common;C:\Program Files\NVIDIA Corporation\NVIDIA NvDLISR;C:\Users\auror\AppData\Roaming\Python\Python37\Scripts;C:\Users\auror\AppData\Local\GitHubDesktop\bin;c:\users\auror\appdata\local\programs\python\python37-32\lib\site-packages\numpy\.libs;c:\users\auror\appdata\local\programs\python\python37-32\lib\site-packages\scipy\.libs;C:\Users\auror\Documents\GitHub\FRC-2020\Characterization\characterization-project\build\tmp\jniExtractDir
"C:\Users\Public\wpilib\2021\jdk\bin\java.exe" -Djava.library.path="C:\Users\auror\Documents\GitHub\FRC-2020\Characterization\characterization-project\build\tmp\jniExtractDir" -jar "C:\Users\auror\Documents\GitHub\FRC-2020\Characterization\characterization-project\build\libs\characterization-project.jar"
endlocal