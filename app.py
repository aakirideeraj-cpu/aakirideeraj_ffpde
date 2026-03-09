from flask import Flask, request, jsonify
from flask_cors import CORS
import requests
from datetime import datetime
import winsound

app = Flask(__name__)
CORS(app)

API_KEY = "FFPE2026SECRET"

TOKEN = "7932181458:AAH4En-XhTj7cv4l1mTuhQdZazvn_-bk7sg"
CHAT_ID = "7972106099"


# 🔥 STORE LATEST DATA FOR DASHBOARD
latest_data = {
    "tank": 0,
    "ecu": 0,
    "expected": 0,
    "bus": "",
    "status": "Waiting for Data..."
}

def send_alert(bus_id, mismatch, gps):
    msg = f"""
🚨 FUEL FRAUD ALERT

Bus: {bus_id}
Fuel Loss: {round(mismatch,2)} L
GPS: {gps}
Time: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}
"""
    url = f"https://api.telegram.org/bot{TOKEN}/sendMessage"
    requests.post(url, data={"chat_id": CHAT_ID, "text": msg})


@app.route("/detect", methods=["POST"])
def detect():
    data = request.json

    if data.get("api_key") != API_KEY:
        return jsonify({"status": "Unauthorized"}), 401

    try:
        tank = float(data["tank"])
        ecu = float(data["ecu"])
        expected = float(data["expected"])
        bus_id = data["bus"]
        gps = data.get("gps", "NA")

    except:
        return jsonify({"status": "Invalid Input"}), 400

    mismatch_tank = abs(expected - tank)
    mismatch_ecu = abs(expected - ecu)

    # 🔥 UPDATE DASHBOARD DATA
    latest_data["tank"] = tank
    latest_data["ecu"] = ecu
    latest_data["expected"] = expected
    latest_data["bus"] = bus_id

    if mismatch_tank > 5 or mismatch_ecu > 5:

        loss = max(mismatch_tank, mismatch_ecu)

        send_alert(bus_id, loss, gps)

        for i in range(3):
            winsound.Beep(1200, 500)

        latest_data["status"] = "🚨 FRAUD DETECTED"
                                                           
        return jsonify({"status": "🚨 FRAUD DETECTED"})

    latest_data["status"] = "✅ NORMAL"
    return jsonify({"status": "✅ NORMAL"})


# 🔥 NEW ROUTE FOR WEBSITE AUTO UPDATE
@app.route("/latest", methods=["GET"])
def latest():
    return jsonify(latest_data)


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)