import sys


def main():
    if(len(sys.argv) < 3):
        print "please supply input and output arguments"
    
    inputFile = open(sys.argv[1], "r")
    outputFile = open(sys.argv[2], "w")
    
    curSentiment = "positive"
    curDoc = ""
    
    for line in inputFile:
        line = line.strip()
        if line  == "^^^END^^^":
            print>>outputFile, curSentiment, ", ", curDoc
            curDoc = ""
        elif line == "^^^NEGATIVE^^^":
            curSentiment = "negative"
        elif line == "^^^POSITIVE^^^":
            curSentiment = "positive"
        else:
            curDoc += " " + line.strip()


    inputFile.close()
    outputFile.close()

    
main()
