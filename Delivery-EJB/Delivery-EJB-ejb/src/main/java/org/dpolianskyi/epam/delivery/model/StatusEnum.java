package org.dpolianskyi.epam.delivery.model;

/**
 *
 * @author Likurg2007
 */
public enum StatusEnum {

    STATUS_AVAILABLE,
    STATUS_UNAVAILABLE,
    STATUS_PAID,
    STATUS_UNPAID,
    STATUS_UNDEFINED;

    public static StatusEnum get(int id) {
        StatusEnum status;
        switch (id) {
            case 1:
                status = StatusEnum.STATUS_AVAILABLE;
                break;
            case 2:
                status = StatusEnum.STATUS_UNAVAILABLE;
                break;
            case 3:
                status = StatusEnum.STATUS_PAID;
                break;
            case 4:
                status = StatusEnum.STATUS_UNPAID;
                break;
            default:
                status = StatusEnum.STATUS_UNDEFINED;
                break;
        }
        return status;
    }

    public static String getString(StatusEnum status) {
        if (status != null) {
            switch (status) {
                case STATUS_AVAILABLE:
                    return "Available";
                case STATUS_UNAVAILABLE:
                    return "Unavailable";
                case STATUS_PAID:
                    return "Paid";
                case STATUS_UNPAID:
                    return "Unpaid";
                default:
                    return "Undefined";
            }
        }
        return "Undefined";
    }

    public static int get(StatusEnum status) {
        if (status != null) {
            switch (status) {
                case STATUS_UNDEFINED:
                    return 0;
                case STATUS_AVAILABLE:
                    return 1;
                case STATUS_UNAVAILABLE:
                    return 2;
                case STATUS_PAID:
                    return 3;
                case STATUS_UNPAID:
                    return 4;
                default:
                    return 0;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return StatusEnum.getString(this);
    }
}
