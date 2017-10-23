# Unix-like Command Line
Nick April <br/>
Operating Systems Assignment <br/>
9/29/17 <br/>

This program is a basic command line environment for interacting with a file system implemented with threads.
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

### Example 1                         

Welcome to the Unix-ish command line.   <br/>
\> cat hello.txt <br/>
&nbsp;&nbsp;&nbsp;Hello     <br/>
&nbsp;&nbsp;&nbsp;world!    <br/>
\> not-a-command    <br/>
The command [not-a-command] was not recognized.   <br/>
\> cat hello.txt | grep world           <br/>
world!    <br/>
\> cat hello.txt | grep world \> output.txt       <br/>
\> cat output.txt   <br/>
world!    <br/>
\> ls     <br/>
&nbsp;&nbsp;&nbsp;bin       <br/>
&nbsp;&nbsp;&nbsp;hello.txt           <br/>
&nbsp;&nbsp;&nbsp;output.txt          <br/>
&nbsp;&nbsp;&nbsp;src       <br/>
\> exit   <br/>
Thank you for using the Unix-ish command line. Goodbye!     <br/>

### Example 2 

Welcome to the Unix-ish command line.   <br/>
\> cat Amazon-reviews.txt | grep amazing \> amazing.txt &   <br/>
\> cat Amazon-reviews.txt | grep defective \> defective.txt &         <br/>
\> repl_jobs        <br/>
 &nbsp;&nbsp;&nbsp;   1. cat Amazon-reviews.txt | grep amazing \> amazing.txt &         <br/>
 &nbsp;&nbsp;&nbsp;   2. cat Amazon-reviews.txt | grep defective \> defective.txt &     <br/>
\> exit   <br/>
Thank you for using the Unix-ish command line. Goodbye!
