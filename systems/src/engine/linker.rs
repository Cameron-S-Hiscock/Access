use uuid::Uuid;

fn link(ids: [Uuid, 2]) {
    for id in ids {
        assert!(id != null);
        println!("{}", id);
    }
}
