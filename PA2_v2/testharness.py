import os
import subprocess
import re
import argparse

JAVA_EXT = r'java -D.*\n*'

parser = argparse.ArgumentParser(description='Process some integers.')

parser.add_argument('path', type=str, nargs=1,
                   help='a path to a cool file or a director of cool files')

def isdirectory(path):
    return os.path.isdir(path)

def get_paths(path):
    if isdirectory(path):
         paths = subprocess.check_output("ls %s" % path, shell=True)
         paths = [path + "/" + p for p in paths.split("\n") if (".cl" in p)]
         return paths
    else:
         return [path]

def main(path):
    paths = get_paths(path)
    success = 0
    fails = 0
    for cl in paths:
        cmd = "python %slexer.py %s"
        reference = subprocess.check_output(cmd % ("", cl), shell=True)
        reference = re.sub(JAVA_EXT, "", reference)
        myout = subprocess.check_output(cmd % ("my", cl), shell=True)
        res = "fail"
        if myout == reference:
            success += 1
            res = "success"
        else:
            fails += 1
        print("test %s : %s" % (cl, res))
    print("")
    print("%s Tests" % str(success + fails))
    print("%s Successs" % str(success))
    print("%s Failures" % str(fails))

args = parser.parse_args()
main(args.path[0])