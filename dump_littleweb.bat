@echo off
echo '###################'
echo 'dump database start...'
mysqldump -h localhost -u littleweb -p littleweb > littleweb.sql
echo 'dump database over...'
pause