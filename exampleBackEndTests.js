import { Meteor } from 'meteor/meteor';

if (Meteor.isServer) {
  describe('UsersCollection', function () {

    // Reset database before each test to ensure isolation
    beforeEach(function () {
      resetDatabase();
    });

    it('inserts a new user with correct attributes', function () {
      const userId = UsersCollection.insert({
        username: 'TestUser',
        email: 'test@example.com',
        firstName: 'Test',
        lastName: 'User',
        year: 'Sophomore',
        major: 'Computer Science'
      });

      const user = UsersCollection.findOne(userId);
      assert.strictEqual(user.username, 'TestUser');
      assert.strictEqual(user.email, 'test@example.com');
    });

    it('prevents duplicate usernames', function () {
      UsersCollection.insert({
        username: 'UniqueUser',
        email: 'unique@example.com'
      });

      let errorThrown = false;
      try {
        UsersCollection.insert({
          username: 'UniqueUser',
          email: 'other@example.com'
        });
      } catch (e) {
        errorThrown = true;
      }
      assert.strictEqual(errorThrown, true, 'Duplicate username insertion should throw error');
    });

    it('retrieves buddy requests correctly', function () {
      const userId = UsersCollection.insert({ username: 'BuddyTester' });
      const requestId = UsersCollection.insert({
        requesterId: userId,
        receiverId: 'SomeOtherUserId',
        status: 'pending'
      });

      const request = UsersCollection.findOne(requestId);
      assert.strictEqual(request.status, 'pending');
      assert.strictEqual(request.requesterId, userId);
    });

  });
}
