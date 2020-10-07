#!/usr/bin/python
import subprocess

from flask import Flask, request

app = Flask("my_app1")

@app.route("/rail")
def rail_schedule():
    if 'outformat' in request.args:
        outformat = request.args.get('outformat')
    else:
        outformat = "html"

    if 'date' in request.args:
        date = request.args.get('date')
    else:
        date = "10/10/2010"

    if 'origin' in request.args:
        origin = request.args.get('origin')
    else:
        origin = "a"

    if 'destination' in request.args:
        destination = request.args.get('destination')
    else:
        destination = "a"

    if 'time' in request.args:
        time = request.args.get('time')
    else:
        time = "00:00"

    return subprocess.check_output(["java", "-classpath", "/home/vv01fy/Studies/Rail_IL", "User", outformat, date, origin, destination, time])

app.run(port=7152,host="0.0.0.0")
