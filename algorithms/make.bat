cd ..
@echo off
:: 사용자에게 폴더 이름 입력받기
set /p "FolderName=프로젝트 폴더 이름을 입력하세요: "

:: 폴더 생성
mkdir "%FolderName%"
cd "%FolderName%"

:: Main.java 파일 생성 (기본 자바 코드 작성)
type NUL > Main.java

:: 빈 i.txt 파일 생성
type NUL > i.txt

echo.
echo [완료] %FolderName% 폴더 안에 Main.java와 i.txt가 생성되었습니다.
pause