import requests
from datetime import datetime

TOKEN = "7932181458:AAH4En-XhTj7cv4l1mTuhQdZazvn_-bk7sg"
CHAT_ID = "7972106099"

def send_alert(bus_id, mismatch):
    msg = f"""
🚨 FUEL FRAUD ALERT

Bus: {bus_id}
Fuel Loss: {mismatch} L
Time: {datetime.now().strftime('%H:%M:%S')}

FFPE Campus Transport Monitor
"""

    url = f"https://api.telegram.org/bot{TOKEN}/sendMessage"
    data = {
        "chat_id": CHAT_ID,
        "text": msg
    }

    r = requests.post(url, data=data)
    print(r.text)

def detect_fraud(tank, expected, bus_id):
    mismatch = abs(expected - tank)

    if mismatch >= 5:
        send_alert(bus_id, mismatch)

detect_fraud(120,130,"Campus Bus 8")
detect_fraud(100,115,"Campus Bus 22")
