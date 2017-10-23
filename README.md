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
- \> <br/>
General Commands
- exit
- & (entered at the end of a command to allow the user to enter an additional command while the previous one runs in the background)
- repl_jobs (prints a list of alive processes)

********************************
          Examples                         
********************************

*** Example1: ***

********************************
Welcome to the Unix-ish command line.   <br/>
\> cat hello.txt <br/>
Hello     <br/>
world!    <br/>
\> not-a-command    <br/>
The command [not-a-command] was not recognized.   <br/>
\> cat hello.txt | grep world           <br/>
world!    <br/>
\> cat hello.txt | grep world \> output.txt       <br/>
\> cat output.txt   <br/>
world!    <br/>
\> ls     <br/>
bin       <br/>
hello.txt           <br/>
output.txt          <br/>
src       <br/>
\> exit   <br/>
Thank you for using the Unix-ish command line. Goodbye!     <br/>
********************************

*** Example2: ***   <br/>
Welcome to the Unix-ish command line.   <br/>
\> cat Amazon-reviews.txt | grep amazing \> amazing.txt &   <br/>
\> cat Amazon-reviews.txt | grep defective \> defective.txt &         <br/>
\> repl_jobs        <br/>
    1. cat Amazon-reviews.txt | grep amazing \> amazing.txt &         <br/>
    2. cat Amazon-reviews.txt | grep defective \> defective.txt &     <br/>
\> exit   <br/>
Thank you for using the Unix-ish command line. Goodbye!
