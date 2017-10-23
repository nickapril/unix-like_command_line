# unix-like_command_line
Nick April
Operating Systems Assignment
9/29/17

A basic command line environment for interacting with a file system implemented with threads.
This program prints a simple prompt (“>   ”), accepts a command (or multiple commands separated by pipes), 
and prints the output from that command (if any).

Fyle System Commands:
- cd
- pwd
- ls
Text Manipulating Commands:
- cat
- grep
- wc
- uniq
- \>
General Commands
- exit
- & (entered at the end of a command to allow the user to enter an additional command while the previous one runs in the background)
- repl_jobs (prints a list of alive processes)

********************************
          Examples                         
********************************

*** Example1: ***

********************************
Welcome to the Unix-ish command line.
> cat hello.txt
Hello
world!
> not-a-command
The command [not-a-command] was not recognized.
> cat hello.txt | grep world
world!
> cat hello.txt | grep world > output.txt
> cat output.txt
world!
> ls
.classpath
.DS_Store
.git
.gitignore
.project
bin
hello.txt
output.txt
src
> exit
Thank you for using the Unix-ish command line. Goodbye!
********************************

*** Example2: ***
Welcome to the Unix-ish command line.
> cat Amazon-reviews.txt | grep amazing > amazing.txt &
> cat Amazon-reviews.txt | grep defective > defective.txt & 
> repl_jobs
    1. cat Amazon-reviews.txt | grep amazing > amazing.txt &
    2. cat Amazon-reviews.txt | grep defective > defective.txt &
> exit
Thank you for using the Unix-ish command line. Goodbye!
