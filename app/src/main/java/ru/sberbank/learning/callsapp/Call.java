package ru.sberbank.learning.callsapp;

/**
 * Created by Тичер on 10.06.2017.
 */
public class Call {

    public long id;
    public int duration;
    public long date;
    public boolean read;
    public String number;
    public int type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Call call = (Call) o;

        if (id != call.id) return false;
        if (duration != call.duration) return false;
        if (date != call.date) return false;
        if (read != call.read) return false;
        if (type != call.type) return false;
        return number != null ? number.equals(call.number)
                : call.number == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + duration;
        result = 31 * result + (int) (date ^ (date >>> 32));
        result = 31 * result + (read ? 1 : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + type;
        return result;
    }
}
