use uuid::Uuid;

pub struct Message {
    id: Uuid,
    origin: Uuid,
    destination: Uuid,
}

impl Message {
    fn check_destination(&self, place: Uuid) -> bool {
        return place == self.destination
    }

    fn check_origin(&self, place: Uuid) -> bool {
        return place == self.origin
    }
}
