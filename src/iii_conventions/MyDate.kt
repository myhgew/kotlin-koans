package iii_conventions


data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

operator fun MyDate.compareTo(other: MyDate): Int {
//    if (this.year == other.year) {
//        if (this.month == other.month) {
//            return this.dayOfMonth - other.dayOfMonth
//        } else {
//            return this.month - other.month
//        }
//    } else {
//        return this.year - other.year
//    }

    return when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange {
    return DateRange(this, other)
}

operator fun MyDate.plus(timeInterval: TimeInterval) : MyDate = this.addTimeIntervals(timeInterval, 1)
operator fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval) : MyDate = this.addTimeIntervals(repeatedTimeInterval.timeInterval, repeatedTimeInterval.number)






enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)
operator fun TimeInterval.times(number: Int) : RepeatedTimeInterval = RepeatedTimeInterval(this, number)





class DateRange(val start: MyDate, val endInclusive: MyDate)

operator fun DateRange.contains(d: MyDate): Boolean {
    return d >= start && d <= endInclusive
}

operator fun DateRange.iterator(): Iterator<MyDate> = DateIterator(this)

class DateIterator(val dateRange: DateRange) : Iterator<MyDate> {
    var current: MyDate = dateRange.start
    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }
    override fun hasNext(): Boolean = current <= dateRange.endInclusive
}